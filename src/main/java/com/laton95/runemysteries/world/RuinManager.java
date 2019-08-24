package com.laton95.runemysteries.world;

import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.util.MathsHelper;
import com.laton95.runemysteries.util.ModLog;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.EndChunkGenerator;
import net.minecraft.world.gen.NetherChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RuinManager extends WorldSavedData {
	private long seed = 0;
	private Map<EnumRuneType, BlockPos> ruinPositions = new HashMap<>();
	private Map<DimensionType, Boolean> generatedDimension = new HashMap<>();
	private String dataFolder = "data/runemysteries.dat";
	
	public RuinManager() {
		super("ruin_manager");
	}
	
	@SubscribeEvent
	public void save(WorldEvent.Save event) {
		ServerWorld world = (ServerWorld) event.getWorld();
		
		if(world != null) {
			this.save(new File(world.getSaveHandler().getWorldDirectory(), dataFolder));
		}
	}
	
	
	public boolean isRuinInChunk(EnumRuneType rune, ChunkGenerator generator, ChunkPos pos) {
		BlockPos ruinBlockPos = getRuinPosition(rune, generator);
		ChunkPos ruinChunkPos = ruinBlockPos != null ? new ChunkPos(ruinBlockPos) : null;
		return pos != null && pos.equals(ruinChunkPos);
	}
	
	@Nullable
	public BlockPos getRuinPosition(EnumRuneType rune, ChunkGenerator<?> generator) {
		IWorld world = getWorldFromDimension(generator);
		DimensionType dimension = world.getDimension().getType();
		
		if(!dimension.isVanilla()) {
			return null;
		}
		
		if(seed != generator.getSeed()) {
			reload((ServerWorld) world);
		}
		
		if(!(generatedDimension.get(dimension) == null ? false : generatedDimension.get(dimension))) {
			calculateRuinPositions(generator);
			generatedDimension.put(dimension, true);
		}
		
		return ruinPositions.get(rune);
	}
	
	@Nullable
	public BlockPos getRuinPosition(EnumRuneType rune, ServerWorld world) {
		if(seed != world.getServer().getWorld(DimensionType.OVERWORLD).getSeed()) {
			reload(world);
		}
		
		return ruinPositions.get(rune);
	}
	
	private void reload(ServerWorld world) {
		ruinPositions = new HashMap<>();
		generatedDimension = new HashMap<>();
		seed = world.getSeed();
		
		ModLog.info("Loading ruin positions");
		long timer = System.currentTimeMillis();
		try {
			read(load(world));
		}
		catch(IOException e) {
			ModLog.error("Failed to load ruin positions: " + e.getMessage());
		}
		ModLog.info(String.format("Ruin positions loaded, took %d milliseconds", System.currentTimeMillis() - timer));
	}
	
	public void setRuinPosition(EnumRuneType rune, BlockPos pos) {
		ruinPositions.put(rune, pos);
		this.setDirty(true);
	}
	
	public void calculateRuinPositions(ChunkGenerator<?> generator) {
		ModLog.info("Calculating ruin positions");
		long timer = System.currentTimeMillis();
		
		Random rand = new Random(generator.getSeed());
		int minRange = 500;
		int maxRange = 10000;
		
		long ruinTimeTotal = 0;
		
		if(generator instanceof OverworldChunkGenerator) {
			for(EnumRuneType rune : EnumRuneType.values()) {
				if(rune != EnumRuneType.CHAOS && rune != EnumRuneType.COSMIC) {
					long timer2 = System.currentTimeMillis();
					calculateRuinPosition(generator, rand, rune, minRange, maxRange);
					ruinTimeTotal += System.currentTimeMillis() - timer2;
				}
			}
		}
		else if(generator instanceof NetherChunkGenerator) {
			calculateRuinPosition(generator, rand, EnumRuneType.CHAOS, minRange, maxRange);
		}
		else if(generator instanceof EndChunkGenerator) {
			calculateRuinPosition(generator, rand, EnumRuneType.COSMIC, minRange, maxRange);
		}
		
		this.setDirty(true);
		ModLog.info(String.format("Ruin positions found, took %d milliseconds", System.currentTimeMillis() - timer));
		ModLog.info(String.format("Average ruin time: %d milliseconds", ruinTimeTotal / EnumRuneType.values().length));
	}
	
	private void calculateRuinPosition(ChunkGenerator<?> generator, Random rand, EnumRuneType rune, int minRange, int maxRange) {
		int maxTries = 15;
		int biomeSearchRange = 200;
		
		BlockPos pos = null;
		
		//Try to get ruin position in a correct biome
		int tries = 0;
		while(pos == null) {
			if(tries >= maxTries) {
				ModLog.info("Trying fallback 1 for " + rune.name());
				break;
			}
			
			int x = MathsHelper.randomInRangeRandomSign(rand, minRange, maxRange);
			int z = MathsHelper.randomInRangeRandomSign(rand, minRange, maxRange);
			
			pos = generator.getBiomeProvider().findBiomePosition(x, z, biomeSearchRange, RuinBiomeManager.getBiomes(rune), rand);
			
			tries++;
		}
		
		//Fallback 1, try to get ruin position in any normal biome
		while(pos == null) {
			if(tries >= maxTries*2) {
				break;
			}
			
			int x = MathsHelper.randomInRangeRandomSign(rand, minRange, maxRange);
			int z = MathsHelper.randomInRangeRandomSign(rand, minRange, maxRange);
			pos = generator.getBiomeProvider().findBiomePosition(x, z, biomeSearchRange, RuinBiomeManager.ouraniaRuinBiomes, rand);
			
			tries++;
		}
		
		//Fallback 2, set ruin position at random location
		if(pos == null) {
			ModLog.info("Trying fallback 2 for " + rune.name());
			int x = MathsHelper.randomInRangeRandomSign(rand, minRange, maxRange);
			int z = MathsHelper.randomInRangeRandomSign(rand, minRange, maxRange);
			pos = new BlockPos(x, 100, z);
			ModLog.info("Found " + rune.name());
		}
		else {
			ModLog.info(String.format("%s location found in %d tries", rune.name(), tries));
		}
		
		if(pos.getY() == 0) {
			pos = pos.add(0, 100, 0);
		}
		
		ruinPositions.put(rune, pos);
		ModLog.info(String.format("%s at x:%d z:%d", rune.name(), pos.getX(), pos.getZ()));
	}
	
	private IWorld getWorldFromDimension(ChunkGenerator generator) {
		IWorld world;
		
		world = ObfuscationReflectionHelper.getPrivateValue(ChunkGenerator.class, generator, "world");
		
		return world;
	}
	
	@Override
	public void read(CompoundNBT compound) {
		for(EnumRuneType rune : EnumRuneType.values()) {
			if(compound.contains(rune.name())) {
				int[] coords = compound.getIntArray(rune.name());
				BlockPos pos = new BlockPos(coords[0], coords[1], coords[2]);
				ruinPositions.put(EnumRuneType.valueOf(rune.name()), pos);
			}
		}
		
		generatedDimension.put(DimensionType.OVERWORLD, compound.getBoolean(DimensionType.OVERWORLD.getRegistryName().toString()));
		generatedDimension.put(DimensionType.THE_NETHER, compound.getBoolean(DimensionType.THE_NETHER.getRegistryName().toString()));
		generatedDimension.put(DimensionType.THE_END, compound.getBoolean(DimensionType.THE_END.getRegistryName().toString()));
		
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		for (Map.Entry<EnumRuneType, BlockPos> ruin : ruinPositions.entrySet()) {
			compound.putIntArray(ruin.getKey().name(), new int[] {ruin.getValue().getX(), ruin.getValue().getY(), ruin.getValue().getZ()});
		}
		for (Map.Entry<DimensionType, Boolean> generated : generatedDimension.entrySet()) {
			compound.putBoolean(generated.getKey().getRegistryName().toString(), generated.getValue());
		}
		return compound;
	}
	
	public CompoundNBT load(ServerWorld world) throws IOException {
		File dataFile = new File(world.getSaveHandler().getWorldDirectory(), dataFolder);
		
		CompoundNBT data;
		try (PushbackInputStream pushbackinputstream = new PushbackInputStream(new FileInputStream(dataFile), 2)) {
			data = CompressedStreamTools.readCompressed(pushbackinputstream);
			data = data.getCompound("data");
		}

		return data;
	}
}
