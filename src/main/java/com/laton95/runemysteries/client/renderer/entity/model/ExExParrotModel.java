package com.laton95.runemysteries.client.renderer.entity.model;

import com.laton95.runemysteries.entity.passive.ExExParrotEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExExParrotModel extends EntityModel<ExExParrotEntity> {
	
	private final RendererModel body;

	private final RendererModel tail;

	private final RendererModel wingLeft;

	private final RendererModel wingRight;

	private final RendererModel head;

	private final RendererModel head2;

	private final RendererModel beak1;

	private final RendererModel beak2;

	private final RendererModel feather;

	private final RendererModel legLeft;

	private final RendererModel legRight;
	
	public ExExParrotModel() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.body = new RendererModel(this, 2, 8);
		this.body.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
		this.body.setRotationPoint(0.0F, 16.5F, -3.0F);
		this.tail = new RendererModel(this, 22, 1);
		this.tail.addBox(-1.5F, -1.0F, -1.0F, 3, 4, 1);
		this.tail.setRotationPoint(0.0F, 21.07F, 1.16F);
		this.wingLeft = new RendererModel(this, 19, 8);
		this.wingLeft.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3);
		this.wingLeft.setRotationPoint(1.5F, 16.94F, -2.76F);
		this.wingRight = new RendererModel(this, 19, 8);
		this.wingRight.addBox(-0.5F, 0.0F, -1.5F, 1, 5, 3);
		this.wingRight.setRotationPoint(-1.5F, 16.94F, -2.76F);
		this.head = new RendererModel(this, 2, 2);
		this.head.addBox(-1.0F, -1.5F, -1.0F, 2, 3, 2);
		this.head.setRotationPoint(0.0F, 15.69F, -2.76F);
		this.head2 = new RendererModel(this, 10, 0);
		this.head2.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 4);
		this.head2.setRotationPoint(0.0F, -2.0F, -1.0F);
		this.head.addChild(this.head2);
		this.beak1 = new RendererModel(this, 11, 7);
		this.beak1.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1);
		this.beak1.setRotationPoint(0.0F, -0.5F, -1.5F);
		this.head.addChild(this.beak1);
		this.beak2 = new RendererModel(this, 16, 7);
		this.beak2.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
		this.beak2.setRotationPoint(0.0F, -1.75F, -2.45F);
		this.head.addChild(this.beak2);
		this.feather = new RendererModel(this, 2, 18);
		this.feather.addBox(0.0F, -4.0F, -2.0F, 0, 5, 4);
		this.feather.setRotationPoint(0.0F, -2.15F, 0.15F);
		this.head.addChild(this.feather);
		this.legLeft = new RendererModel(this, 14, 18);
		this.legLeft.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
		this.legLeft.setRotationPoint(1.0F, 22.0F, -1.05F);
		this.legRight = new RendererModel(this, 14, 18);
		this.legRight.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
		this.legRight.setRotationPoint(-1.0F, 22.0F, -1.05F);
	}
	
	public void render(ExExParrotEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.body.render(scale);
		this.wingLeft.render(scale);
		this.wingRight.render(scale);
		this.tail.render(scale);
		this.head.render(scale);
		this.legLeft.render(scale);
		this.legRight.render(scale);
	}
	
	public void setRotationAngles(ExExParrotEntity parrot, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		int ticksExisted = parrot.ticksExisted;
		
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.head.rotateAngleZ = 0.0F;
		this.head.rotationPointX = 0.0F;
		this.body.rotationPointX = 0.0F;
		this.tail.rotationPointX = 0.0F;
		this.wingRight.rotationPointX = -1.5F;
		this.wingLeft.rotationPointX = 1.5F;
		switch(getState(parrot)) {
			case SITTING:
				break;
			case PARTY:
				float xOffset = MathHelper.cos((float) ticksExisted);
				float yOffset = MathHelper.sin((float) ticksExisted);
				this.head.rotationPointX = xOffset;
				this.head.rotationPointY = 15.69F + yOffset;
				this.head.rotateAngleX = 0.0F;
				this.head.rotateAngleY = 0.0F;
				this.head.rotateAngleZ = MathHelper.sin((float) ticksExisted) * 0.4F;
				this.body.rotationPointX = xOffset;
				this.body.rotationPointY = 16.5F + yOffset;
				this.wingLeft.rotateAngleZ = -0.0873F - ageInTicks;
				this.wingLeft.rotationPointX = 1.5F + xOffset;
				this.wingLeft.rotationPointY = 16.94F + yOffset;
				this.wingRight.rotateAngleZ = 0.0873F + ageInTicks;
				this.wingRight.rotationPointX = -1.5F + xOffset;
				this.wingRight.rotationPointY = 16.94F + yOffset;
				this.tail.rotationPointX = xOffset;
				this.tail.rotationPointY = 21.07F + yOffset;
				break;
			case STANDING:
				this.legLeft.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
				this.legRight.rotateAngleX += MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			case FLYING:
			default:
				float reducedAge = ageInTicks * 0.3F;
				this.head.rotationPointY = 15.69F + reducedAge;
				this.tail.rotateAngleX = 1.015F + MathHelper.cos(limbSwing * 0.6662F) * 0.3F * limbSwingAmount;
				this.tail.rotationPointY = 21.07F + reducedAge;
				this.body.rotationPointY = 16.5F + reducedAge;
				this.wingLeft.rotateAngleZ = -0.0873F - ageInTicks;
				this.wingLeft.rotationPointY = 16.94F + reducedAge;
				this.wingRight.rotateAngleZ = 0.0873F + ageInTicks;
				this.wingRight.rotationPointY = 16.94F + reducedAge;
				this.legLeft.rotationPointY = 22.0F + reducedAge;
				this.legRight.rotationPointY = 22.0F + reducedAge;
		}
	}
	
	public void setLivingAnimations(ExExParrotEntity parrot, float limbSwing, float limbSwingAmount, float partialTick) {
		this.feather.rotateAngleX = -0.2214F;
		this.body.rotateAngleX = 0.4937F;
		this.wingLeft.rotateAngleX = -0.6981F;
		this.wingLeft.rotateAngleY = -(float) Math.PI;
		this.wingRight.rotateAngleX = -0.6981F;
		this.wingRight.rotateAngleY = -(float) Math.PI;
		this.legLeft.rotateAngleX = -0.0299F;
		this.legRight.rotateAngleX = -0.0299F;
		this.legLeft.rotationPointY = 22.0F;
		this.legRight.rotationPointY = 22.0F;
		this.legLeft.rotateAngleZ = 0.0F;
		this.legRight.rotateAngleZ = 0.0F;
		switch(getState(parrot)) {
			case SITTING:
				float f = 1.9F;
				this.head.rotationPointY = 17.59F;
				this.tail.rotateAngleX = 1.5388988F;
				this.tail.rotationPointY = 22.97F;
				this.body.rotationPointY = 18.4F;
				this.wingLeft.rotateAngleZ = -0.0873F;
				this.wingLeft.rotationPointY = 18.84F;
				this.wingRight.rotateAngleZ = 0.0873F;
				this.wingRight.rotationPointY = 18.84F;
				++this.legLeft.rotationPointY;
				++this.legRight.rotationPointY;
				++this.legLeft.rotateAngleX;
				++this.legRight.rotateAngleX;
				break;
			case PARTY:
				this.legLeft.rotateAngleZ = -0.34906584F;
				this.legRight.rotateAngleZ = 0.34906584F;
			case STANDING:
			default:
				break;
			case FLYING:
				this.legLeft.rotateAngleX += 0.6981317F;
				this.legRight.rotateAngleX += 0.6981317F;
		}
	}
	
	private static State getState(ExExParrotEntity parrot) {
		if(parrot.isPartying()) {
			return State.PARTY;
		}
		else if(parrot.isSitting()) {
			return State.SITTING;
		}
		else {
			return parrot.isFlying() ? State.FLYING : State.STANDING;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public enum State {
		FLYING,
		STANDING,
		SITTING,
		PARTY
	}
}
