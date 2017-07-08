package com.laton95.runemysteries.utility;

import java.util.Random;

import com.laton95.runemysteries.reference.Reference;
import com.laton95.runemysteries.reference.WorldGenReference;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldHelper {

	public static void loadStructure(BlockPos pos, World world, Template template) {
		PlacementSettings settings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID);
		template.addBlocksToWorldChunk(world, pos.add(0, 1, 0), settings);
	}

	public static void loadStructure(BlockPos pos, World world, Template template, PlacementSettings settings) {
		template.addBlocksToWorldChunk(world, pos.add(0, 1, 0), settings);
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

	@Deprecated
	public static BlockPos findSurface(World worldIn, int x, int z) {
		for (int i = worldIn.getActualHeight(); i > 0; i--) {
			IBlockState currentBlockState = worldIn.getBlockState(new BlockPos(x, i, z));
			Block currentBlock = currentBlockState.getBlock();
			if (!currentBlock.equals(Blocks.AIR)) {
				if (currentBlockState.getMaterial().isLiquid()) {
					return null;
				} else if (WorldGenReference.surfaceBlocks.contains(currentBlock)) {
					return new BlockPos(x, i, z);
				} else
					return null;
			}
		}
		return null;
	}

	public static float determineFlatness(World worldIn, BlockPos position, int xSize, int ySize, int zSize) {
		int airBlocksBelow = 0;
		int solidBlocksFirstLayer = 0;
		int solidBlocksOverhead = 0;

		for (int y = 0; y < ySize + 1; y++) {
			for (int x = 0; x < xSize; x++) {
				for (int z = 0; z < zSize; z++) {
					int xPos = position.getX() + x;
					int zPos = position.getZ() + z;
					int yPos = position.getY() + y;
					BlockPos pos = new BlockPos(xPos, yPos, zPos);
					Block block = worldIn.getBlockState(pos).getBlock();
					if (y == 0
							&& (block.equals(Blocks.AIR) || block.equals(Blocks.WATER) || block.equals(Blocks.LAVA))) {
						airBlocksBelow++;
					} else if (y == 1
							&& !(block.equals(Blocks.AIR) || block.equals(Blocks.WATER) || block.equals(Blocks.LAVA))) {
						solidBlocksFirstLayer++;
					} else if (y > 1
							&& !(block.equals(Blocks.AIR) || block.equals(Blocks.WATER) || block.equals(Blocks.LAVA))) {
						solidBlocksOverhead++;
					}
				}
			}
		}

		

		if (solidBlocksFirstLayer > xSize * zSize * 0.5)
			return 0;
		if (airBlocksBelow > xSize * zSize * 0.1)
			return 0;

		int airWeight = 3;
		int solidWeight = 1;
		
		int solidBlocksAbove = solidBlocksFirstLayer + solidBlocksOverhead;
		
		return 1 - ((airBlocksBelow * airWeight + solidBlocksAbove * solidWeight)
				/ (xSize * zSize * airWeight + xSize * zSize * ySize * solidWeight));
	}

	public abstract static class ModFeature extends StructureComponent {
		/** The size of the bounding box for this feature in the X axis */
		protected int width;
		/** The size of the bounding box for this feature in the Y axis */
		protected int height;
		/** The size of the bounding box for this feature in the Z axis */
		protected int depth;
		protected int horizontalPos = -1;

		public ModFeature() {
		}

		protected ModFeature(Random rand, int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
			super(0);
			this.width = sizeX;
			this.height = sizeY;
			this.depth = sizeZ;
			this.setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

			if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
				this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
			} else {
				this.boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
			}
		}

		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			tagCompound.setInteger("Width", this.width);
			tagCompound.setInteger("Height", this.height);
			tagCompound.setInteger("Depth", this.depth);
			tagCompound.setInteger("HPos", this.horizontalPos);
		}

		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			this.width = tagCompound.getInteger("Width");
			this.height = tagCompound.getInteger("Height");
			this.depth = tagCompound.getInteger("Depth");
			this.horizontalPos = tagCompound.getInteger("HPos");
		}

		/**
		 * Calculates and offsets this structure boundingbox to average ground
		 * level
		 */
		public boolean offsetToAverageGroundLevel(World worldIn, StructureBoundingBox structurebb, int yOffset) {
			if (this.horizontalPos >= 0) {
				return true;
			} else {
				int i = 0;
				int j = 0;
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k) {
					for (int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l) {
						blockpos$mutableblockpos.setPos(l, 64, k);

						if (structurebb.isVecInside(blockpos$mutableblockpos)) {
							i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(),
									worldIn.provider.getAverageGroundLevel());
							++j;
						}
					}
				}

				if (j == 0) {
					return false;
				} else {
					this.horizontalPos = i / j;
					this.boundingBox.offset(0, this.horizontalPos - this.boundingBox.minY + yOffset, 0);
					return true;
				}
			}
		}
	}
}