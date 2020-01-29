package com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.LookUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Stack;

@EventBusSubscriber(Dist.CLIENT)
public class HealthBarRenderer {

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        try {

            if (true) {
                return;
            }

            Minecraft mc = Minecraft.getInstance();

            if ((!ClientContainer.INSTANCE.neatConfig.renderInF1.get() && !Minecraft.isGuiEnabled()) || !NeatConfig.draw)
                return;

            Entity cameraEntity = mc.getRenderViewEntity();

            BlockPos renderingVector = cameraEntity.getPosition();

            float partialTicks = event.getPartialTicks();
            double viewX = cameraEntity.lastTickPosX + (cameraEntity.posX - cameraEntity.lastTickPosX) * partialTicks;
            double viewY = cameraEntity.lastTickPosY + (cameraEntity.posY - cameraEntity.lastTickPosY) * partialTicks;
            double viewZ = cameraEntity.lastTickPosZ + (cameraEntity.posZ - cameraEntity.lastTickPosZ) * partialTicks;

            if (ClientContainer.INSTANCE.neatConfig.showOnlyFocused.get()) {
                Entity focused = LookUtils.getEntityLookedAt(mc.player);

                if (focused != null && focused instanceof LivingEntity && focused.isAlive())
                    renderHealthBar((LivingEntity) focused, partialTicks, cameraEntity, event.getMatrixStack());
            } else {

                Int2ObjectMap<Entity> entitiesById = mc.world.entitiesById;
                for (Entity entity : entitiesById.values()) {
                    if (entity != null && entity instanceof LivingEntity && entity != mc.player && entity.isInRangeToRender3d(
                            renderingVector.getX(), renderingVector.getY(),
                            renderingVector.getZ()
                    ) && entity.isAlive() && entity.getRecursivePassengers().isEmpty())
                        renderHealthBar((LivingEntity) entity, partialTicks, cameraEntity, event.getMatrixStack());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void renderHealthBar(LivingEntity en, float partialTicks, Entity viewPoint, MatrixStack matrix) {
        Stack<LivingEntity> ridingStack = new Stack<>();

        LivingEntity entity = en;
        ridingStack.push(entity);

        while (entity.getRidingEntity() != null && entity.getRidingEntity() instanceof LivingEntity) {
            entity = (LivingEntity) entity.getRidingEntity();
            ridingStack.push(entity);
        }

        Minecraft mc = Minecraft.getInstance();

        float pastTranslate = 0F;
        while (!ridingStack.isEmpty()) {
            entity = ridingStack.pop();
            boolean boss = !entity.isNonBoss();

            String entityID = entity.getEntityString();

            processing:
            {
                float distance = en.getDistance(viewPoint);
                if (distance > ClientContainer.INSTANCE.neatConfig.maxDistance.get() || !en.canEntityBeSeen(
                        viewPoint) || entity.isInvisible())
                    break processing;
                if (!ClientContainer.INSTANCE.neatConfig.showOnBosses.get() && !boss)
                    break processing;
                if (!ClientContainer.INSTANCE.neatConfig.showOnPlayers.get() && entity instanceof PlayerEntity)
                    break processing;

                double x = en.posX;
                double y = en.posY;
                double z = en.posZ;

                float scale = 0.026666672F;
                float maxHealth = entity.getMaxHealth();
                float health = Math.min(maxHealth, entity.getHealth());

                if (maxHealth <= 0)
                    break processing;

                float percent = (int) ((health / maxHealth) * 100F);

                //double renderPosX = ObfuscationReflectionHelper.getPrivateValue(
                //       EntityRendererManager.class, renderManager, "renderPosX");

                EntityRendererManager renderManager = Minecraft.getInstance().getRenderManager();

                Vec3d pos = renderManager.info.getProjectedView();

                PlayerEntity p = mc.player;

                Vector3d dist = new Vector3d(x - p.posX, y - p.posY, z - p.posZ);

                double renderPosX = pos.getX() - dist.x;
                double renderPosY = pos.getY() - dist.y;
                double renderPosZ = pos.getZ() - dist.z;

                GlStateManager.pushMatrix();
                GlStateManager.translatef((float) (renderPosX),
                                          (float) (renderPosY + en.getHeight() + ClientContainer.INSTANCE.neatConfig.heightAbove
                                                  .get()), (float) (renderPosZ)
                );

                GL11.glNormal3f(0.0F, 1.0F, 0.0F);

                GlStateManager.rotatef((float) -renderManager.info.getViewVector().getY(), 0.0F, 1.0F, 0.0F);
                GlStateManager.rotatef((float) renderManager.info.getViewVector().getY(), 1.0F, 0.0F, 0.0F);// TODO

                GlStateManager.scalef(-scale, -scale, scale);
                boolean lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
                GlStateManager.disableLighting();
                GlStateManager.depthMask(false);
                GlStateManager.disableDepthTest();
                GlStateManager.disableTexture();
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder buffer = tessellator.getBuffer();

                float padding = ClientContainer.INSTANCE.neatConfig.backgroundPadding.get();
                int bgHeight = ClientContainer.INSTANCE.neatConfig.backgroundHeight.get();
                int barHeight = ClientContainer.INSTANCE.neatConfig.barHeight.get();
                float size = boss ? ClientContainer.INSTANCE.neatConfig.plateSizeBoss.get() :
                        ClientContainer.INSTANCE.neatConfig.plateSize
                        .get();

                int r = 0;
                int g = 255;
                int b = 0;

                ItemStack stack = ItemStack.EMPTY;

                int armor = entity.getTotalArmorValue();

                boolean useHue = !ClientContainer.INSTANCE.neatConfig.colorByType.get();
                if (useHue) {
                    float hue = Math.max(0F, (health / maxHealth) / 3F - 0.07F);
                    Color color = Color.getHSBColor(hue, 1F, 1F);
                    r = color.getRed();
                    g = color.getGreen();
                    b = color.getBlue();
                }

                GlStateManager.translatef(0F, pastTranslate, 0F);

                float s = 0.5F;
                String name = I18n.format(entity.getDisplayName().getFormattedText());
                if (entity instanceof LivingEntity && entity.hasCustomName())
                    name = TextFormatting.ITALIC + entity.getCustomName().toString();
                else if (entity instanceof VillagerEntity)
                    name = I18n.format("entity.Villager.name");

                float namel = mc.fontRenderer.getStringWidth(name) * s;
                if (namel + 20 > size * 2)
                    size = namel / 2F + 10F;
                float healthSize = size * (health / maxHealth);

                // Background
                if (ClientContainer.INSTANCE.neatConfig.drawBackground.get()) {
                    buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                    buffer.pos(-size - padding, -bgHeight, 0.0D).color(0, 0, 0, 64).endVertex();
                    buffer.pos(-size - padding, barHeight + padding, 0.0D).color(0, 0, 0, 64).endVertex();
                    buffer.pos(size + padding, barHeight + padding, 0.0D).color(0, 0, 0, 64).endVertex();
                    buffer.pos(size + padding, -bgHeight, 0.0D).color(0, 0, 0, 64).endVertex();
                    tessellator.draw();
                }

                // Gray Space
                buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(-size, 0, 0.0D).color(127, 127, 127, 127).endVertex();
                buffer.pos(-size, barHeight, 0.0D).color(127, 127, 127, 127).endVertex();
                buffer.pos(size, barHeight, 0.0D).color(127, 127, 127, 127).endVertex();
                buffer.pos(size, 0, 0.0D).color(127, 127, 127, 127).endVertex();
                tessellator.draw();

                // Health Bar
                buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(-size, 0, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(-size, barHeight, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(healthSize * 2 - size, barHeight, 0.0D).color(r, g, b, 127).endVertex();
                buffer.pos(healthSize * 2 - size, 0, 0.0D).color(r, g, b, 127).endVertex();
                tessellator.draw();

                GlStateManager.enableTexture();

                GlStateManager.pushMatrix();
                GlStateManager.translatef(-size, -4.5F, 0F);
                GlStateManager.scalef(s, s, s);
                mc.fontRenderer.drawString(name, 0, 0, 0xFFFFFF);

                GlStateManager.pushMatrix();
                float s1 = 0.75F;
                GlStateManager.scalef(s1, s1, s1);

                int h = ClientContainer.INSTANCE.neatConfig.hpTextHeight.get();
                String maxHpStr = TextFormatting.BOLD + "" + Math.round(maxHealth * 100.0) / 100.0;
                String hpStr = "" + Math.round(health * 100.0) / 100.0;
                String percStr = (int) percent + "%";

                if (maxHpStr.endsWith(".0"))
                    maxHpStr = maxHpStr.substring(0, maxHpStr.length() - 2);
                if (hpStr.endsWith(".0"))
                    hpStr = hpStr.substring(0, hpStr.length() - 2);

                if (ClientContainer.INSTANCE.neatConfig.showCurrentHP.get())
                    mc.fontRenderer.drawString(hpStr, 2, h, 0xFFFFFF);
                if (ClientContainer.INSTANCE.neatConfig.showMaxHP.get())
                    mc.fontRenderer.drawString(maxHpStr,
                                               (int) (size / (s * s1) * 2) - 2 - mc.fontRenderer.getStringWidth(
                                                       maxHpStr), h, 0xFFFFFF
                    );
                if (ClientContainer.INSTANCE.neatConfig.showPercentage.get())
                    mc.fontRenderer.drawString(percStr,
                                               (int) (size / (s * s1)) - mc.fontRenderer.getStringWidth(percStr) / 2, h,
                                               0xFFFFFFFF
                    );

                GlStateManager.popMatrix();

                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                int off = 0;

                s1 = 0.5F;
                GlStateManager.scalef(s1, s1, s1);
                GlStateManager.translatef(size / (s * s1) * 2 - 16, 0F, 0F);
                mc.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);

                // render icons here

                GlStateManager.popMatrix();

                GlStateManager.disableBlend();
                GlStateManager.enableDepthTest();
                GlStateManager.depthMask(true);
                if (lighting)
                    GlStateManager.enableLighting();
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();

                pastTranslate -= bgHeight + barHeight + padding;
            }
        }
    }

    public static void renderIcon(int vertexX, int vertexY, ItemStack stack, int intU, int intV) {
        try {
            Minecraft mc = Minecraft.getInstance();
            IBakedModel iBakedModel = mc.getItemRenderer().getItemModelMesher().getItemModel(stack);

            TextureAtlasSprite textureAtlasSprite = mc.getTextureGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE)
                    .apply(iBakedModel.getParticleTexture().getName());

            mc.getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(7, DefaultVertexFormats.POSITION_TEX);

            buffer.pos((double) (vertexX), (double) (vertexY + intV), 0.0D)
                    .tex((float) textureAtlasSprite.getMinU(), (float) textureAtlasSprite.getMaxV())
                    .endVertex();
            buffer.pos((double) (vertexX + intU), (double) (vertexY + intV), 0.0D).
                    tex((float) textureAtlasSprite.getMaxU(), (float) textureAtlasSprite.getMaxV()).endVertex();
            buffer.pos((double) (vertexX + intU), (double) (vertexY), 0.0D)
                    .tex((float) textureAtlasSprite.getMaxU(), (float) textureAtlasSprite.getMinV())
                    .endVertex();
            buffer.pos((double) (vertexX), (double) (vertexY), 0.0D)
                    .tex((float) textureAtlasSprite.getMinU(), (float) textureAtlasSprite.getMinV())
                    .endVertex();
            tessellator.draw();
        } catch (Exception e) {
        }
    }

}
