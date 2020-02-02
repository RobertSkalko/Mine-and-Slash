package com.robertx22.mine_and_slash.a_libraries.dmg_number_particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class DamageParticle extends Particle {

    protected String text;
    protected float scale = 0.7F;
    Elements element;
    public boolean grow = true;

    float locX;
    float locY;
    float locZ;

    boolean positionNeedsSetting = true;

    public DamageParticle(Elements element, String str, World world, double parX, double parY, double parZ,
                          double parMotionX, double parMotionY, double parMotionZ) {
        super(world, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);

        particleGravity = ClientContainer.INSTANCE.dmgParticleConfig.GRAVITY.get().floatValue();
        scale = ClientContainer.INSTANCE.dmgParticleConfig.START_SIZE.get().floatValue();
        this.maxAge = ClientContainer.INSTANCE.dmgParticleConfig.LIFESPAN.get().intValue();
        this.text = element.format + element.icon + TextFormatting.GRAY + str;
        this.element = element;
    }

    public void setupPosition(ActiveRenderInfo info) {
        Minecraft mc = Minecraft.getInstance();

        float speed = ClientContainer.INSTANCE.dmgParticleConfig.SPEED.get().floatValue();

        PlayerEntity p = mc.player;

        Vec3d view = info.getProjectedView();

        Vector3d distance = new Vector3d(posX - p.posX, posY - p.posY, posZ - p.posZ);

        locX = ((float) (this.prevPosX + (this.posX - this.prevPosX) * posX - view.getX())) * speed;
        locY = ((float) (this.prevPosY + (this.posY - this.prevPosY) * posY - view.getY())) * speed;
        locZ = ((float) (this.prevPosZ + (this.posZ - this.prevPosZ) * posZ - view.getZ())) * speed;

        positionNeedsSetting = false;

    }

    @Override
    public void renderParticle(IVertexBuilder vertex, ActiveRenderInfo info, float partialTicks) {

        try {

            float rotationYaw = (-Minecraft.getInstance().player.rotationYaw);
            float rotationPitch = Minecraft.getInstance().player.rotationPitch;
            Vec3d view = info.getProjectedView();
            float posX = (float) (MathHelper.lerp((double) partialTicks, this.prevPosX, this.posX) - view.getX());
            float posY = (float) (MathHelper.lerp((double) partialTicks, this.prevPosY, this.posY) - view.getY());
            float posZ = (float) (MathHelper.lerp((double) partialTicks, this.prevPosZ, this.posZ) - view.getZ());

            RenderSystem.pushMatrix();

            GL11.glTranslated(posX, posY - 0.5F, posZ);
            GL11.glRotatef(rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(rotationPitch, 1.0F, 0.0F, 0.0F);

            GL11.glScaled(-1.0, -1.0, 1.0);
            GL11.glScaled(this.getBoundingBox().getXSize() * 0.04D, this.getBoundingBox().getYSize() * 0.04D,
                          this.getBoundingBox().getZSize() * 0.04D
            );
            GL11.glScaled(1.0, 1.0, 1.0);

            RenderSystem.scaled(this.scale, this.scale, this.scale);

            FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;

            RenderSystem.disableColorMaterial();
            RenderSystem.disableLighting();
            RenderSystem.depthMask(false);
            RenderSystem.disableDepthTest();

            fontRenderer.drawStringWithShadow(this.text, 0, 0, element.format.getColor());

            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableLighting();
            RenderSystem.enableColorMaterial();

            RenderSystem.popMatrix();

            this.setSize(1.0F, 1.0F);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        if (ClientContainer.INSTANCE.dmgParticleConfig.GROWS.get()) {
            if (this.grow) {
                this.scale *= 1.05F;
                if (this.scale > ClientContainer.INSTANCE.dmgParticleConfig.MAX_SIZE.get().floatValue()) {
                    this.grow = false;
                }
            } else {
                this.scale *= 0.97F;
            }
        }
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        }

        this.prevParticleAngle = this.particleAngle;
        this.particleAngle += (float) Math.PI * 0.2 * 2.0F;
        if (this.onGround) {
            this.prevParticleAngle = this.particleAngle = 0.0F;
        }

        this.move(this.motionX, this.motionY, this.motionZ);

        double speed = ClientContainer.INSTANCE.dmgParticleConfig.SPEED.get();

        this.motionY -= speed;
        this.motionX += speed * world.rand.nextDouble();
        this.motionZ += speed * world.rand.nextDouble();

        this.motionY = Math.max(this.motionY, -0.14D);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.CUSTOM;
    }
}