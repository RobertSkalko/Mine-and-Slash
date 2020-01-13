package com.robertx22.mine_and_slash.particles;

import net.minecraft.client.particle.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DripEleParticle extends SpriteTexturedParticle implements ParticleType<EleParticleData> {
    private final Fluid fluid;

    public DripEleParticle(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.fluid = Fluids.FLOWING_WATER;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getBrightnessForRender(float p_189214_1_) {
        return this.fluid.isIn(FluidTags.LAVA) ? 240 : super.getBrightnessForRender(p_189214_1_);
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.checkDeath();
        if (!this.isExpired) {
            this.motionY -= (double) this.particleGravity;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.onDeath();
            if (!this.isExpired) {
                this.motionX *= 0.9800000190734863D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ *= 0.9800000190734863D;
                BlockPos lvt_1_1_ = new BlockPos(this.posX, this.posY, this.posZ);
                IFluidState lvt_2_1_ = this.world.getFluidState(lvt_1_1_);
                if (lvt_2_1_.getFluid() == this.fluid && this.posY < (double) ((float) lvt_1_1_
                        .getY() + lvt_2_1_.getActualHeight(this.world, lvt_1_1_))) {
                    this.setExpired();
                }

            }
        }
    }

    protected void checkDeath() {
        if (this.maxAge-- <= 0) {
            this.setExpired();
        }

    }

    protected void onDeath() {
    }

    @OnlyIn(Dist.CLIENT)
    public static class DrippingElementalFactory implements IParticleFactory<BasicParticleType> {
        protected final IAnimatedSprite spriteProvider;

        public DrippingElementalFactory(IAnimatedSprite sprite) {
            this.spriteProvider = sprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType type, World world, double x,
                                     double y, double z, double mx, double my,
                                     double mz) {
            DripEleParticle particle = new DripEleParticle(world, x, y, z);
            particle.particleGravity = 0.01F;
            particle.setColor(0.582F, 0.448F, 0.082F);
            particle.selectSpriteRandomly(this.spriteProvider);
            return particle;
        }
    }

}
