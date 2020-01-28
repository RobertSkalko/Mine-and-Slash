package com.robertx22.mine_and_slash.a_libraries.dmg_number_particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.robertx22.mine_and_slash.config.ClientContainer;
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
    public void renderParticle(IVertexBuilder vertex, ActiveRenderInfo info, float f) {

        try {

            float rotationYaw = (-Minecraft.getInstance().player.rotationYaw);
            float rotationPitch = Minecraft.getInstance().player.rotationPitch;

            Minecraft mc = Minecraft.getInstance();

            float speed = ClientContainer.INSTANCE.dmgParticleConfig.SPEED.get().floatValue();

            PlayerEntity p = mc.player;

            Vec3d view = info.getProjectedView();

            Vector3d distance = new Vector3d(posX - p.posX, posY - p.posY, posZ - p.posZ);

            if (positionNeedsSetting) {
                setupPosition(info);
            }

            //            MatrixStack matrix = new MatrixStack();

            // matrix.push();

            RenderSystem.pushMatrix();

            RenderSystem.depthFunc(519);

            RenderSystem.translated(locX + distance.x, locY + distance.y - p.getEyeHeight(), locZ + distance.z);

            RenderSystem.rotatef(rotationYaw, 0.0F, 1.0F, 0.0F);
            RenderSystem.rotatef(rotationPitch, 1.0F, 0.0F, 0.0F);

            RenderSystem.scalef(-1.0F, -1.0F, 1.0F);

            RenderSystem.scaled(this.scale * 0.008D, this.scale * 0.008D, this.scale * 0.008D);
            RenderSystem.scaled(this.scale, this.scale, this.scale);

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

            final FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            fontRenderer.drawStringWithShadow(this.text,
                                              -MathHelper.floor(fontRenderer.getStringWidth(this.text) / 2.0F) + 1,
                                              -MathHelper.floor(fontRenderer.FONT_HEIGHT / 2.0F) + 1,
                                              element.format.getColor()
            );

            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.depthFunc(515);

            RenderSystem.popMatrix();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.CUSTOM;
    }
}