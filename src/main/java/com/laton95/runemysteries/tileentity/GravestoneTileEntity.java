package com.laton95.runemysteries.tileentity;

import com.laton95.runemysteries.init.ModTileEntities;
import com.laton95.runemysteries.util.ModLog;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.Function;

public class GravestoneTileEntity extends TileEntity {
	
	private ITextComponent text = new StringTextComponent("RIP");
	
	private String renderText;
	
	private DyeColor textColour = DyeColor.BLACK;
	
	public GravestoneTileEntity() {
		super(ModTileEntities.GRAVESTONE);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		super.write(nbt);
		
		String s = ITextComponent.Serializer.toJson(text);
		nbt.putString("text", s);
		
		nbt.putString("colour", this.textColour.getTranslationKey());
		return nbt;
	}
	
	@Override
	public void read(CompoundNBT nbt) {
		super.read(nbt);
		this.textColour = DyeColor.byTranslationKey(nbt.getString("colour"), DyeColor.BLACK);
		
		String s = nbt.getString("text");
		ITextComponent itextcomponent = ITextComponent.Serializer.fromJson(s.isEmpty() ? "\"\"" : s);
		if(this.world instanceof ServerWorld) {
			try {
				text = TextComponentUtils.updateForEntity(this.getCommandSource(null), itextcomponent, null, 0);
			}
			catch(CommandSyntaxException var6) {
				text = itextcomponent;
			}
		}
		else {
			text = itextcomponent;
		}
		
		this.renderText = null;
	}
	
	@OnlyIn(Dist.CLIENT)
	public ITextComponent getText() {
		ModLog.info(text);
		return text;
	}
	
	public void setText(ITextComponent text) {
		this.text = text;
		renderText = null;
	}
	
	@Nullable
	@OnlyIn(Dist.CLIENT)
	public String getRenderText(Function<ITextComponent, String> function) {
		if(renderText == null && text != null) {
			renderText = function.apply(text);
		}
		
		return renderText;
	}
	
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
	}
	
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}
	
	public CommandSource getCommandSource(@Nullable ServerPlayerEntity playerIn) {
		String s = playerIn == null ? "Sign" : playerIn.getName().getString();
		ITextComponent itextcomponent = playerIn == null ? new StringTextComponent("Sign") : playerIn.getDisplayName();
		return new CommandSource(ICommandSource.DUMMY, new Vec3d((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D), Vec2f.ZERO, (ServerWorld) this.world, 2, s, itextcomponent, this.world.getServer(), playerIn);
	}
	
	public DyeColor getTextColour() {
		return this.textColour;
	}
	
	public boolean setTextColour(DyeColor newColor) {
		if(newColor != this.getTextColour()) {
			this.textColour = newColor;
			this.markDirty();
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
			return true;
		}
		else {
			return false;
		}
	}
}
