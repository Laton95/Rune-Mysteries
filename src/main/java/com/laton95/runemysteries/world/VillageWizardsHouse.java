package com.laton95.runemysteries.world;

import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.init.VillagerRegistry;
import com.laton95.runemysteries.util.LogHelper;
import com.laton95.runemysteries.util.StructureHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillageWizardsHouse extends Village
{

	private EnumFacing facing;

	public VillageWizardsHouse(Start villagePiece, int par2, Random par3Random,
			StructureBoundingBox par4StructureBoundingBox, EnumFacing facing)
	{
		super(villagePiece, par2);
		setCoordBaseMode(facing);
		boundingBox = par4StructureBoundingBox;
		this.facing = facing;
	}

	private int groundLevel = -1;

	@Override
	public boolean addComponentParts(World worldIn, Random rand, StructureBoundingBox structureBoundingBoxIn)
	{
		StructureBoundingBox bBox = boundingBox;
		structureBoundingBoxIn = boundingBox;
		if (groundLevel < 0)
		{
			groundLevel = getAverageGroundLevel(worldIn, bBox);
			if (groundLevel < 0)
			{
				return true;
			}
			boundingBox.offset(0, groundLevel - boundingBox.maxY + 10 - 1, 0);
			BlockPos pos = new BlockPos(bBox.minX, bBox.minY, bBox.minZ);
			PlacementSettings settings = new PlacementSettings().setBoundingBox(boundingBox).setReplacedBlock(
					Blocks.STRUCTURE_VOID);
			StructureHelper structureHelper = new StructureHelper(worldIn, "wizard_house", pos, settings);
			structureHelper.generate();
			LogHelper.info(pos + " " + facing);
		}

		return true;
	}

	@Override
	protected VillagerProfession chooseForgeProfession(int count, VillagerProfession prof)
	{
		return VillagerRegistry.villagerProfession_Wizard;
	}

	public static class VillageManager implements IVillageCreationHandler
	{

		@Override
		public PieceWeight getVillagePieceWeight(Random random, int i)
		{
			return new PieceWeight(VillageWizardsHouse.class, 100, MathHelper.getInt(random, 0 + i, 1 + i));
		}

		@Override
		public Class<?> getComponentClass()
		{
			return VillageWizardsHouse.class;
		}

		@Override
		public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5)
		{
			StructureBoundingBox box = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 8, 6, 11,
					facing);
			return !canVillageGoDeeper(box) || StructureComponent.findIntersecting(pieces, box) != null ? null
					: new VillageWizardsHouse(startPiece, p5, random, box, facing);
		}

	}
}
