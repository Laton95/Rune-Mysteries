package com.laton95.runemysteries.util;

import java.util.Random;
import java.util.UUID;

import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.world.WorldGenerator;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class WorldHelper {

	public static void loadStructure(BlockPos pos, World world, Template template) {
		PlacementSettings settings = new PlacementSettings().setReplacedBlock(Blocks.STRUCTURE_VOID);
		template.addBlocksToWorld(world, pos.add(0, 1, 0), settings);
	}

	public static void loadStructure(BlockPos pos, World world, Template template, PlacementSettings settings) {
		template.addBlocksToWorld(world, pos.add(0, 1, 0), settings);
	}

	public static Template getTemplate(World world, String name) {
		if (!world.isRemote) {
			MinecraftServer minecraftserver = world.getMinecraftServer();
			TemplateManager templatemanager = world.getSaveHandler().getStructureTemplateManager();
			ResourceLocation loc = new ResourceLocation(ModReference.MOD_ID, name);
			return templatemanager.getTemplate(minecraftserver, loc);
		}
		return null;
	}

	public static boolean isFlat(World worldIn, BlockPos position, int xSize, int ySize, int zSize, int airWeight,
			int solidWeight, float flatnessTolerance) {
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

		if (solidBlocksFirstLayer > xSize * zSize * (1 - flatnessTolerance / 2)) {
			return false;
		}
		if (airBlocksBelow > xSize * zSize * (1 - flatnessTolerance)) {
			return false;
		}

		int solidBlocksAbove = solidBlocksFirstLayer + solidBlocksOverhead;

		return 1 - (airBlocksBelow * airWeight + solidBlocksAbove * solidWeight)
				/ (xSize * zSize * airWeight + xSize * zSize * ySize * solidWeight) > flatnessTolerance;
	}

	public static boolean isOverGround(World world, BlockPos pos, int xSize, int zSize) {
		for (int x = 0; x < xSize; x++) {
			for (int z = 0; z < zSize; z++) {
				for (int y = world.getActualHeight(); y > 0; y--) {
					if (!world.getBlockState(new BlockPos(pos.getX() + x, y, pos.getZ() + z)).getBlock()
							.equals(Blocks.AIR)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isInsideCave(World world, BlockPos pos, int xSize, int zSize) {
		int solidBlocks = 0;
		for (int x = 0; x < xSize; x++) {
			for (int z = 0; z < zSize; z++) {
				for (int y = 0; y < 10; y++) {
					if (!world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z)).getBlock()
							.equals(Blocks.AIR)) {
						solidBlocks++;
						break;
					}
				}
				for (int y = 0; y < 10; y++) {
					if (!world.getBlockState(new BlockPos(pos.getX() + x, pos.getY() - y, pos.getZ() + z)).getBlock()
							.equals(Blocks.AIR)) {
						solidBlocks++;
						break;
					}
				}
			}
		}
		return solidBlocks >= xSize * zSize;
	}

	public static boolean isNearby(ChunkPos chunkA, ChunkPos chunkB, int range) {
		int x = Math.abs(chunkA.x - chunkB.x);
		int z = Math.abs(chunkA.z - chunkB.z);

		if (Math.max(z, x) < range) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNearby(BlockPos blockA, BlockPos blockB, int range) {
		int x = Math.abs(blockA.getX() - blockB.getX());
		int z = Math.abs(blockA.getZ() - blockB.getZ());
		int y = Math.abs(blockA.getY() - blockB.getY());

		return Math.max(Math.max(z, x), y * 2) < range;
	}

	public static Direction getDirection(BlockPos from, BlockPos too) {
		int x = from.getX() - too.getX(); // East/West
		int y = from.getY() - too.getY(); // Up/Down
		int z = from.getZ() - too.getZ(); // North/South

		double horizontalTheta = Math.atan(Math.abs((double) x / z));
		double verticalXTheta = Math.atan(Math.abs((double) x / y));
		double verticalZTheta = Math.atan(Math.abs((double) z / y));

		if (Math.max(verticalXTheta, verticalZTheta) < 0.1 * Math.PI) {
			if (y < 0) {
				return Direction.UP;
			} else {
				return Direction.DOWN;
			}
		}
		if (z == 0) {
			if (x < 0) {
				return Direction.EAST;
			} else {
				return Direction.WEST;
			}
		}
		if (x == 0) {
			if (z < 0) {
				return Direction.SOUTH;
			} else {
				return Direction.NORTH;
			}
		}
		if (z > 0 && x > 0) {
			// North-West quadrant
			if (horizontalTheta < 0.125 * Math.PI) {
				return Direction.NORTH;
			} else if (horizontalTheta < 0.365 * Math.PI) {
				return Direction.NORTH_WEST;
			} else {
				return Direction.WEST;
			}
		}
		if (z > 0 && x < 0) {
			// North-East quadrant
			if (horizontalTheta < 0.125 * Math.PI) {
				return Direction.NORTH;
			} else if (horizontalTheta < 0.365 * Math.PI) {
				return Direction.NORTH_EAST;
			} else {
				return Direction.EAST;
			}
		}
		if (z < 0 && x > 0) {
			// South-West quadrant
			if (horizontalTheta < 0.125 * Math.PI) {
				return Direction.SOUTH;
			} else if (horizontalTheta < 0.365 * Math.PI) {
				return Direction.SOUTH_WEST;
			} else {
				return Direction.WEST;
			}
		}
		if (z < 0 && x < 0) {
			// South-East quadrant
			if (horizontalTheta < 0.125 * Math.PI) {
				return Direction.SOUTH;
			} else if (horizontalTheta < 0.365 * Math.PI) {
				return Direction.SOUTH_EAST;
			} else {
				return Direction.EAST;
			}
		}

		return Direction.UNKNOWN;
	}
	
	public static void TeleportEntityToDimension(Entity entityIn, World worldIn, int dimID, float x, float y, float z){
		if (!worldIn.isRemote && worldIn.provider.getDimension() != dimID) {
			WorldTeleporter teleporter = new WorldTeleporter(entityIn.getServer().getWorld(dimID), new BlockPos(x, y, z));
			if (entityIn instanceof EntityPlayerMP) {
				entityIn.setPosition(x, y, z);
				entityIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) entityIn, dimID, teleporter);
			} else {
				changeDimension(entityIn, dimID, teleporter);
			}
		}
	}
	
	private static void changeDimension(Entity entity, int toDimID, WorldTeleporter teleporter)
    {
        if (!entity.world.isRemote && !entity.isDead)
        {
            if (!net.minecraftforge.common.ForgeHooks.onTravelToDimension(entity, toDimID)) return;
            entity.world.profiler.startSection("changeDimension");
            MinecraftServer minecraftserver = entity.getServer();
            int fromDimID = entity.dimension;
            WorldServer fromWorldserver = minecraftserver.getWorld(fromDimID);
            WorldServer toWorldServer = minecraftserver.getWorld(toDimID);
            entity.dimension = toDimID;

            entity.world.removeEntity(entity);
            entity.isDead = false;
            entity.world.profiler.startSection("reposition");

            teleporter.placeInPortal(entity, entity.rotationYaw);

            fromWorldserver.updateEntityWithOptionalForce(entity, false);
            entity.world.profiler.endStartSection("reloading");
            Entity entity2 = EntityList.newEntity(entity.getClass(), toWorldServer);
            
            if (entity2 != null)
            {
            	NBTTagCompound compound = entity.writeToNBT(new NBTTagCompound());
                entity2.readFromNBT(compound);
                compound.removeTag("Dimension");
                entity.readFromNBT(compound);

                boolean flag = entity2.forceSpawn;
                entity2.forceSpawn = true;
                toWorldServer.spawnEntity(entity2);
                entity2.forceSpawn = flag;
                toWorldServer.updateEntityWithOptionalForce(entity2, false);
            }

            entity.isDead = true;
            entity.world.profiler.endSection();
            fromWorldserver.resetUpdateEntityTick();
            toWorldServer.resetUpdateEntityTick();
            entity.world.profiler.endSection();
        }
    }

	public enum Direction {
		UP, DOWN, NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST, UNKNOWN
	}

	public enum dimType {
		OVERWORLD, NETHER, END
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
			width = sizeX;
			height = sizeY;
			depth = sizeZ;
			setCoordBaseMode(EnumFacing.Plane.HORIZONTAL.random(rand));

			if (getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
				boundingBox = new StructureBoundingBox(x, y, z, x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1);
			} else {
				boundingBox = new StructureBoundingBox(x, y, z, x + sizeZ - 1, y + sizeY - 1, z + sizeX - 1);
			}
		}

		/**
		 * (abstract) Helper method to write subclass data to NBT
		 */
		@Override
		protected void writeStructureToNBT(NBTTagCompound tagCompound) {
			tagCompound.setInteger("Width", width);
			tagCompound.setInteger("Height", height);
			tagCompound.setInteger("Depth", depth);
			tagCompound.setInteger("HPos", horizontalPos);
		}

		/**
		 * (abstract) Helper method to read subclass data from NBT
		 */
		@Override
		protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {
			width = tagCompound.getInteger("Width");
			height = tagCompound.getInteger("Height");
			depth = tagCompound.getInteger("Depth");
			horizontalPos = tagCompound.getInteger("HPos");
		}

		public void setBoundingBox(StructureBoundingBox box) {
			boundingBox = box;
		}

		/**
		 * Calculates and offsets this structure boundingbox to average ground
		 * level
		 */
		public boolean offsetToAverageGroundLevel(World worldIn, StructureBoundingBox structurebb, int yOffset) {
			if (horizontalPos >= 0) {
				return true;
			} else {
				int i = 0;
				int j = 0;
				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int k = boundingBox.minZ; k <= boundingBox.maxZ; ++k) {
					for (int l = boundingBox.minX; l <= boundingBox.maxX; ++l) {
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
					horizontalPos = i / j;
					boundingBox.offset(0, horizontalPos - boundingBox.minY + yOffset, 0);
					return true;
				}
			}
		}
	}
}