package com.robertx22.mine_and_slash.a_libraries.dmg_number_particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.util.math.MathHelper;
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
    public void buildGeometry(IVertexBuilder vertex, ActiveRenderInfo info, float var3) {

        try {
            float rotationYaw = (-Minecraft.getInstance().player.rotationYaw);
            float rotationPitch = Minecraft.getInstance().player.rotationPitch;

            float speed = ClientContainer.INSTANCE.dmgParticleConfig.SPEED.get()
                    .floatValue();

            // TODO UNSURE IF info.getBlockPos().getX() is good
            final float locX = ((float) (this.prevPosX + (this.posX - this.prevPosX) * 1)) * speed;
            final float locY = ((float) (this.prevPosY + (this.posY - this.prevPosY) * 1)) * speed;
            final float locZ = ((float) (this.prevPosZ + (this.posZ - this.prevPosZ) * 1)) * speed;

            GL11.glPushMatrix();

            GL11.glTranslatef(locX, locY, locZ);
            GL11.glRotatef(rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(rotationPitch, 1.0F, 0.0F, 0.0F);

            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            GL11.glScaled(this.scale * 0.008D, this.scale * 0.008D, this.scale * 0.008D);
            GL11.glScaled(this.scale, this.scale, this.scale);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            final FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
            fontRenderer.drawStringWithShadow(this.text, -MathHelper.floor(fontRenderer.getStringWidth(this.text) / 2.0F) + 1, -MathHelper
                    .floor(fontRenderer.FONT_HEIGHT / 2.0F) + 1, element.format.getColor());

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

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
    public IParticleRenderType getRenderType() { // custom?
        return IParticleRenderType.CUSTOM; // TODO WTF IS THIS
    }
}