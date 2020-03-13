package com.robertx22.mine_and_slash.gui.bar_overlays.types;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.PlayerGUIs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VanillaOverlay extends IngameGui {

    public VanillaOverlay(Minecraft mc) {
        super(mc);
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {

        try {

            if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
                return;
            }

            if (!ClientContainer.INSTANCE.PLAYER_GUI_TYPE.get()
                .equals(PlayerGUIs.Vanilla)) {
                return;
            }

            Minecraft mc = Minecraft.getInstance();
            PlayerEntity en = mc.player;
            UnitData data = Load.Unit(en);

            if (en.isCreative() || en.isSpectator()) {
                return;
            }

            ResourceLocation TEX = new ResourceLocation("mmorpg", "textures/gui/overlay/vanilla_overlay.png");

            mc.getTextureManager()
                .bindTexture(TEX);

            int SPACING_Y = 10;

            int width = event.getWindow()
                .getScaledWidth();
            int height = event.getWindow()
                .getScaledHeight();

            int x = width / 2 - 91;
            int y = height - 39 - SPACING_Y;

            if (en.getTotalArmorValue() > 0) {
                y -= SPACING_Y;
            }

            float ticks = event.getPartialTicks();

            int leftY = ClientContainer.INSTANCE.LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST.get();
            int rightY = ClientContainer.INSTANCE.RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST.get();

            renderElement(ticks, Type.MAGIC_SHIELD, x, y + leftY, mc, en, data);

            x = width / 2 + 81;
            y = height - 39 - SPACING_Y;

            renderElement(ticks, Type.MANA, x, y + rightY, mc, en, data);

            y -= SPACING_Y;

            renderElement(ticks, Type.ENERGY, x, y + rightY, mc, en, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum Type {

        MANA {
            @Override
            public Side getSide() {
                return Side.RIGHT;
            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {
                if (data.getUnit()
                    .isBloodMage()) {
                    return data.getResources()
                        .getBlood();
                } else {
                    return data.getResources()
                        .getMana();
                }
            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                if (data.getUnit()
                    .isBloodMage()) {
                    return data.getUnit()
                        .getMaximumBlood();
                } else {
                    return data.getUnit()
                        .manaData().val;
                }
            }

            @Override
            public int yPosTexture(UnitData data) {
                if (data.getUnit()
                    .isBloodMage()) {
                    return 30;
                } else {
                    return 10;
                }
            }
        },
        ENERGY {
            @Override
            public Side getSide() {
                return Side.RIGHT;

            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {
                return data.getResources()
                    .getEnergy();
            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                return data.getUnit()
                    .energyData().val;
            }

            @Override
            public int yPosTexture(UnitData data) {
                return 20;
            }
        },
        MAGIC_SHIELD {
            @Override
            public Side getSide() {
                return Side.LEFT;
            }

            @Override
            public float getCurrent(LivingEntity en, UnitData data) {
                return data.getResources()
                    .getMagicShield();
            }

            @Override
            public float getMax(LivingEntity en, UnitData data) {
                return data.getUnit()
                    .magicShieldData().val;
            }

            @Override
            public int yPosTexture(UnitData data) {
                return 0;
            }
        };

        public abstract Side getSide();

        public abstract float getCurrent(LivingEntity en, UnitData data);

        public abstract float getMax(LivingEntity en, UnitData data);

        public abstract int yPosTexture(UnitData data);

    }

    enum Side {
        LEFT, RIGHT;
    }

    public void renderElement(float ticks, Type type, int x, int y, Minecraft mc, LivingEntity en, UnitData data) {

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.enableBlend();

        int current = (int) type.getCurrent(en, data);
        int max = (int) type.getMax(en, data);

        boolean needsRegen = current < max;

        if (max < 1) {
            return;
        }
        int tenth = max / 10;

        int X_SPACING = 8;

        int halfSpacing = type.getSide()
            .equals(Side.LEFT) ? 0 : +5;

        for (int i = 0; i < 10; i++) {

            blit(x, y, 16, type.yPosTexture(data), 9, 9); // empty background

            if (current > 0) {
                if (current >= tenth) { // fullbar
                    blit(x, y, 0, type.yPosTexture(data), 9, 9);
                } else { // half
                    blit(x + halfSpacing, y, 10, type.yPosTexture(data), 5, 9);
                }
            }
            if (type.getSide()
                .equals(Side.LEFT)) {
                x += X_SPACING;
            } else {
                x -= X_SPACING;
            }

            current -= tenth;

        }

        RenderSystem.disableBlend();

    }

}