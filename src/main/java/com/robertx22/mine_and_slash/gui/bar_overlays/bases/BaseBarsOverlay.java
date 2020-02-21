package com.robertx22.mine_and_slash.gui.bar_overlays.bases;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public abstract class BaseBarsOverlay extends AbstractGui {

    public static int BAR_WIDTH = 106;
    public static int BAR_HEIGHT = 11;

    public final static ResourceLocation MANA_TEX = new ResourceLocation("mmorpg", "textures/gui/overlay/mana_bar.png");
    public final static ResourceLocation ENE_TEX = new ResourceLocation(
            "mmorpg", "textures/gui/overlay/energy_bar.png");
    public final static ResourceLocation HP_TEX = new ResourceLocation("mmorpg", "textures/gui/overlay/health_bar.png");
    public final static ResourceLocation EXP_TEX = new ResourceLocation(
            "mmorpg", "textures/gui/overlay/experience_bar.png");
    public final static ResourceLocation MAG_SHIELD_TEX = new ResourceLocation(
            "mmorpg", "textures/gui/overlay/magic_shield_bar.png");
    public final static ResourceLocation BLOOD_TEX = new ResourceLocation(
            "mmorpg", "textures/gui/overlay/blood_bar.png");

    public enum BarType {
        HP {
            @Override
            public ResourceLocation getTex(LivingEntity en, UnitData data) {
                return HP_TEX;
            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {
                return data.getUnit().health().CurrentValue(en, data.getUnit());
            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                return data.getUnit().healthData().val;
            }

            @Override
            public void renderExtra(AbstractGui gui, int x, int y, LivingEntity en, UnitData data) {

                float curMS = data.getResources().getMagicShield();
                if (curMS > 0) {

                    float maxHP = data.getUnit().healthData().val;
                    float maxperc = MathHelper.clamp(curMS / maxHP, 0, 1);

                    int barPercent = (int) ((int) ((curMS / data.getUnit().magicShieldData().val * 100)) * maxperc);
                    Minecraft.getInstance().getTextureManager().bindTexture(MAG_SHIELD_TEX);
                    gui.blit(x + 3, y + 3, 0, BAR_HEIGHT, barPercent, 5);
                }
            }

            @Override
            public float getCurrentForDisplay(LivingEntity en, UnitData data) {
                return data.getUnit().getCurrentEffectiveHealth(en, data);
            }

            @Override
            public float getMaxForDisplay(LivingEntity en, UnitData data) {
                return data.getUnit().getMaxEffectiveHealth();
            }
        },
        MANA {
            @Override
            public ResourceLocation getTex(LivingEntity en, UnitData data) {

                if (data.getUnit().isBloodMage()) {
                    return BLOOD_TEX;
                } else {
                    return MANA_TEX;
                }

            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {

                if (data.getUnit().isBloodMage()) {
                    return data.getResources().getBlood();
                } else {
                    return data.getResources().getMana();
                }

            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                if (data.getUnit().isBloodMage()) {
                    return data.getUnit().getMaximumBlood();
                } else {
                    return data.getUnit().manaData().val;
                }
            }
        },
        ENE {
            @Override
            public ResourceLocation getTex(LivingEntity en, UnitData data) {
                return ENE_TEX;
            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {
                return data.getResources().getEnergy();
            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                return data.getUnit().energyData().val;
            }
        },
        EXP {
            @Override
            public ResourceLocation getTex(LivingEntity en, UnitData data) {
                return EXP_TEX;
            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {
                return data.getExp();
            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                return data.getExpRequiredForLevelUp();
            }

            @Override
            public String getText(LivingEntity en, UnitData data) {
                String now = NumberUtils.formatNumber((int) getCurrentForDisplay(en, data));
                String maximum = NumberUtils.formatNumber((int) getMaxForDisplay(en, data));

                return "Lvl:" + data.getLevel() + " " + now + "/" + maximum;

            }
        };

        BarType() {

        }

        public abstract ResourceLocation getTex(LivingEntity en, UnitData data);

        public int getBarPercent(LivingEntity en, UnitData data) {
            return MathHelper.clamp((int) (((float) getCurrent(en, data) / (float) getMax(en, data) * 100)), 0, 100);

        }

        public abstract float getCurrent(LivingEntity en, UnitData data);

        public abstract float getMax(LivingEntity en, UnitData data);

        public float getCurrentForDisplay(LivingEntity en, UnitData data) {
            return getCurrent(en, data);
        }

        public float getMaxForDisplay(LivingEntity en, UnitData data) {
            return getMax(en, data);
        }

        public String getText(LivingEntity en, UnitData data) {
            String now = NumberUtils.formatNumber((int) getCurrentForDisplay(en, data));
            String maximum = NumberUtils.formatNumber((int) getMaxForDisplay(en, data));

            return now + "/" + maximum;
        }

        public void renderExtra(AbstractGui gui, int x, int y, LivingEntity en, UnitData data) {

        }

    }

    public abstract void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity, RenderGameOverlayEvent event,
                              UnitData level);

    public void DrawBar(BarType type, UnitData data, int x, int y) {

        Minecraft mc = Minecraft.getInstance();
        PlayerEntity p = mc.player;

        float current = type.getCurrent(p, data);
        float max = type.getMax(p, data);

        RenderSystem.color4f(1F, 1F, 1F, 1F);

        mc.getTextureManager().bindTexture(type.getTex(p, data));

        blit(x, y, 0, 0, BAR_WIDTH, this.BAR_HEIGHT); // the bar

        int barPercent = type.getBarPercent(p, data);

        blit(x + 3, y + 3, 0, BAR_HEIGHT, barPercent, 5); // inner fill texture

        type.renderExtra(this, x, y, p, data);

        String text = type.getText(p, data);

        float textX = x + BAR_WIDTH / 2 - mc.fontRenderer.getStringWidth(text) / 2;
        float textY = y + (float) BAR_HEIGHT / 2 - (float) mc.fontRenderer.FONT_HEIGHT / 2 + 0.5F;

        mc.fontRenderer.drawStringWithShadow(text, textX, textY, Color.LIGHT_GRAY.getRGB());

    }

}
