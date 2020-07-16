package com.robertx22.mine_and_slash.gui.overlays;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HeartRenderer extends IngameGui {

    public HeartRenderer(Minecraft mcIn) {
        super(mcIn);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerOverlay(RenderGameOverlayEvent.Pre event) {

        if (true) {
            return;
        }

        if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
            if (event.isCancelable()) {
                event.setCanceled(true);
            }
            tick();

            mc.getTextureManager()
                .bindTexture(GUI_ICONS_LOCATION);
            RenderSystem.enableBlend();

            PlayerEntity player = (PlayerEntity) this.mc.getRenderViewEntity();
            int health = MathHelper.ceil(player.getHealth());
            boolean highlight = healthUpdateCounter > (long) ticks && (healthUpdateCounter - (long) ticks) / 3L % 2L == 1L;

            IAttributeInstance attrMaxHealth = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
            float healthMax = (float) attrMaxHealth.getValue();
            float absorb = MathHelper.ceil(player.getAbsorptionAmount());

            health = (int) ((float) health / (float) healthMax * 20);
            healthMax = 20;
            // make hearts into percentages

            if (health < this.playerHealth && player.hurtResistantTime > 0) {
                this.lastSystemTime = Util.milliTime();
                this.healthUpdateCounter = (long) (this.ticks + 20);
            } else if (health > this.playerHealth && player.hurtResistantTime > 0) {
                this.lastSystemTime = Util.milliTime();
                this.healthUpdateCounter = (long) (this.ticks + 10);
            }

            if (Util.milliTime() - this.lastSystemTime > 1000L) {
                this.playerHealth = health;
                this.lastPlayerHealth = health;
                this.lastSystemTime = Util.milliTime();
            }

            this.playerHealth = health;
            int healthLast = this.lastPlayerHealth;

            int healthRows = MathHelper.ceil((healthMax + absorb) / 2.0F / 10.0F);
            int rowHeight = Math.max(10 - (healthRows - 2), 3);

            this.rand.setSeed((long) (ticks * 312871));

            int left = event.getWindow()
                .getScaledWidth() / 2 - 91;
            int top = event.getWindow()
                .getScaledHeight() - ForgeIngameGui.left_height;
            ForgeIngameGui.left_height += (healthRows * rowHeight);
            if (rowHeight != 10) ForgeIngameGui.left_height += 10 - rowHeight;

            int regen = -1;
            if (player.isPotionActive(Effects.REGENERATION)) {
                regen = ticks % 25;
            }

            final int TOP = 9 * (mc.world.getWorldInfo()
                .isHardcore() ? 5 : 0);
            final int BACKGROUND = (highlight ? 25 : 16);
            int MARGIN = 16;
            if (player.isPotionActive(Effects.POISON)) MARGIN += 36;
            else if (player.isPotionActive(Effects.WITHER)) MARGIN += 72;
            float absorbRemaining = absorb;

            for (int i = MathHelper.ceil((healthMax + absorb) / 2.0F) - 1; i >= 0; --i) {
                //int b0 = (highlight ? 1 : 0);
                int row = MathHelper.ceil((float) (i + 1) / 10.0F) - 1;
                int x = left + i % 10 * 8;
                int y = top - row * rowHeight;

                if (health <= 4) y += rand.nextInt(2);
                if (i == regen) y -= 2;

                blit(x, y, BACKGROUND, TOP, 9, 9);

                if (highlight) {
                    if (i * 2 + 1 < healthLast)
                        blit(x, y, MARGIN + 54, TOP, 9, 9); //6
                    else if (i * 2 + 1 == healthLast)
                        blit(x, y, MARGIN + 63, TOP, 9, 9); //7
                }

                if (absorbRemaining > 0.0F) {
                    if (absorbRemaining == absorb && absorb % 2.0F == 1.0F) {
                        blit(x, y, MARGIN + 153, TOP, 9, 9); //17
                        absorbRemaining -= 1.0F;
                    } else {
                        blit(x, y, MARGIN + 144, TOP, 9, 9); //16
                        absorbRemaining -= 2.0F;
                    }
                } else {
                    if (i * 2 + 1 < health)
                        blit(x, y, MARGIN + 36, TOP, 9, 9); //4
                    else if (i * 2 + 1 == health)
                        blit(x, y, MARGIN + 45, TOP, 9, 9); //5
                }
            }

            RenderSystem.disableBlend();
            // MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(event, HEALTH));
        }
    }
}
