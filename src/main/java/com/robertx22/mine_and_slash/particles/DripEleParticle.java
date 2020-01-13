package com.robertx22.mine_and_slash.particles;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.client.particle.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DripEleParticle extends SpriteTexturedParticle {
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

    public int getBrightnessForRender(float f) {
        return 240;
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
                BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
                IFluidState state = this.world.getFluidState(pos);
                if (state.getFluid() == this.fluid && this.posY < (double) ((float) pos.getY() + state
                        .getActualHeight(this.world, pos))) {
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
    public static class DrippingElementalFactory implements IParticleFactory<EleParticleData> {
        protected final IAnimatedSprite spriteProvider;

        public DrippingElementalFactory(IAnimatedSprite sprite) {
            this.spriteProvider = sprite;
        }

        @Override
        public Particle makeParticle(EleParticleData type, World world, double x,
                                     double y, double z, double mx, double my,
                                     double mz) {
            DripEleParticle particle = new DripEleParticle(world, x, y, z);
            particle.particleGravity = 0.01F;

            Elements.RGB col = type.element.getRGBColor();

            particle.setColor(col.getR(), col.getG(), col.getB());

            particle.selectSpriteRandomly(this.spriteProvider);
            return particle;
        }
    }

}
