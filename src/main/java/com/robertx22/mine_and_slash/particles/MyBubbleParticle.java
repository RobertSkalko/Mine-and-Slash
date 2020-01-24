package com.robertx22.mine_and_slash.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// i copy pasted this cus original bubble particle expires when not in water.
public class MyBubbleParticle extends SpriteTexturedParticle {

    private MyBubbleParticle(World world, double x, double y, double z, double xm, double ym, double zm) {
        super(world, x, y, z);
        this.setSize(0.02F, 0.02F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
        this.motionX = xm * (double) 0.2F + (Math.random() * 2.0D - 1.0D) * (double) 0.02F;
        this.motionY = ym * (double) 0.2F + (Math.random() * 2.0D - 1.0D) * (double) 0.02F;
        this.motionZ = zm * (double) 0.2F + (Math.random() * 2.0D - 1.0D) * (double) 0.02F;
        this.maxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.maxAge-- <= 0) {
            this.setExpired();
        } else {
            this.motionY += 0.002D;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double) 0.85F;
            this.motionY *= (double) 0.85F;
            this.motionZ *= (double) 0.85F;

        }
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle makeParticle(BasicParticleType type, World world, double x, double y, double z, double xm,
                                     double ym, double zm) {
            MyBubbleParticle bubbleparticle = new MyBubbleParticle(world, x, y, z, xm, ym, zm);
            bubbleparticle.selectSpriteRandomly(this.spriteSet);
            return bubbleparticle;
        }
    }
}