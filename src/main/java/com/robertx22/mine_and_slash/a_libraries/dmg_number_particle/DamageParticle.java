package com.robertx22.mine_and_slash.a_libraries.dmg_number_particle;

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
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class DamageParticle extends Particle {

    protected String text;
    protected float scale = 0.7F;
    Elements element;
    public boolean grow = true;

    public DamageParticle(Elements element, String str, World world, double parX,
                          double parY, double parZ, double parMotionX, double parMotionY,
                          double parMotionZ) {
        super(world, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);

        particleGravity = ClientContainer.INSTANCE.dmgParticleConfig.GRAVITY.get()
                .floatValue();
        scale = ClientContainer.INSTANCE.dmgParticleConfig.START_SIZE.get().floatValue();
        this.maxAge = ClientContainer.INSTANCE.dmgParticleConfig.LIFESPAN.get()
                .intValue();
        this.text = element.format + element.icon + TextFormatting.GRAY + str;
        this.element = element;
    }

    @Override
    public void buildGeometry(IVertexBuilder vertex, ActiveRenderInfo info, float f) {

        try {
            float rotationYaw = (-Minecraft.getInstance().player.rotationYaw);
            float rotationPitch = Minecraft.getInstance().player.rotationPitch;

            Minecraft mc = Minecraft.getInstance();

            float speed = ClientContainer.INSTANCE.dmgParticleConfig.SPEED.get()
                    .floatValue();

            PlayerEntity p = mc.player;

            Vec3d view = info.getProjectedView();

            Vector3d distance = new Vector3d(posX - p.posX, posY - p.posY, posZ - p.posZ);

            final float locX = ((float) (this.prevPosX + (this.posX - this.prevPosX) * posX - view
                    .getX())) * speed;
            final float locY = ((float) (this.prevPosY + (this.posY - this.prevPosY) * posY - view
                    .getY())) * speed;
            final float locZ = ((float) (this.prevPosZ + (this.posZ - this.prevPosZ) * posZ - view
                    .getZ())) * speed;

            GL11.glPushMatrix();

            GL11.glDepthFunc(519);

            GL11.glTranslated(locX + distance.x, locY + distance.y - p.getEyeHeight(), locZ + distance.z);

            GL11.glRotatef(rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(rotationPitch, 1.0F, 0.0F, 0.0F);

            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            GL11.glScaled(this.scale * 0.008D, this.scale * 0.008D, this.scale * 0.008D);
            GL11.glScaled(this.scale, this.scale, this.scale);

            //idk
            GL11.glEnable(3553);
            GL11.glDisable(3042);
            GL11.glDepthMask(false);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(2896);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(3042);
            GL11.glEnable(3008);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            final FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            fontRenderer.drawStringWithShadow(this.text, -MathHelper.floor(fontRenderer.getStringWidth(this.text) / 2.0F) + 1, -MathHelper
                    .floor(fontRenderer.FONT_HEIGHT / 2.0F) + 1, element.format.getColor());

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDepthFunc(515);

            GL11.glPopMatrix();
            if (ClientContainer.INSTANCE.dmgParticleConfig.GROWS.get()) {
                if (this.grow) {
                    this.scale *= 1.08F;
                    if (this.scale > ClientContainer.INSTANCE.dmgParticleConfig.MAX_SIZE.get()
                            .floatValue()) {
                        this.grow = false;
                    }
                } else {
                    this.scale *= 0.96F;
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