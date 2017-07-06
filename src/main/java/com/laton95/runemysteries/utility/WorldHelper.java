package com.laton95.runemysteries.utility;

import java.util.Random;

import com.laton95.runemysteries.reference.Config;
import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.reference.WorldGen;
import com.sun.jna.platform.RasterRangesUtils.RangesOutput;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldHelper {
	
	public static void loadStructure(BlockPos pos, World world, Template template) {
		PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
				.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
				.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);

		template.addBlocksToWorldChunk(world, pos.add(0, 1, 0), placementsettings);
	}
	
	public static Template getTemplate(World world, String name) {
		if (!world.isRemote) {
			MinecraftServer minecraftserver = world.getMinecraftServer();
			TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
			ResourceLocation loc = new ResourceLocation(Reference.MOD_ID, name);
			return templatemanager.getTemplate(minecraftserver, loc);
		}
		return null;
	}
	
	public static BlockPos findSurface(World worldIn, int x, int z) {
		for(int i = worldIn.getActualHeight(); i > 0; i--) {
			IBlockState currentBlockState = worldIn.getBlockState(new BlockPos(x, i, z));
			Block currentBlock = currentBlockState.getBlock();
			if (!currentBlock.equals(Blocks.AIR)) {
				if (currentBlockState.getMaterial().isLiquid()) {
					return null;
				}
				else if (WorldGen.surfaceBlocks.contains(currentBlock)) {
					return new BlockPos(x, i, z);
				} else return null;
			}
		}
		return null;
	}
	
	public static float determineFlatness(World worldIn, BlockPos position, int xSize, int ySize, int zSize) {
		int airBlocksBelow = 0;
		int solidBlocksAbove = 0;
		
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < zSize; j++) {
				int x = position.getX() + i;
				int z = position.getZ() + j;
				if (!worldIn.isBlockNormalCube(new BlockPos(x,position.getY(),z), true)) {
					airBlocksBelow++;
				}
			}
		}
		for (int k = 1; k < ySize; k++) {
			for (int i = 0; i < xSize; i++) {
				for (int j = 0; j < zSize; j++) {
					int x = position.getX() + i;
					int z = position.getZ() + j;
					int y = position.getY() + k;
					if (worldIn.isBlockNormalCube(new BlockPos(x,y,z), true)) {
						solidBlocksAbove++;
					}
				}
			}
		}
		
		return 1 - ((airBlocksBelow*2 + solidBlocksAbove)/(xSize*zSize*ySize));
		}
}
