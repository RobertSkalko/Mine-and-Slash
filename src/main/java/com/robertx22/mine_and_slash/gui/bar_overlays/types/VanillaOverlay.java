package com.robertx22.mine_and_slash.gui.bar_overlays.types;

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

            if (en.isCreative()) {
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

            int leftY = ClientContainer.INSTANCE.LEFT_VANILLA_LIKE_BARS_Y__POS_ADJUST.get();
            int rightY = ClientContainer.INSTANCE.RIGHT_VANILLA_LIKE_BARS_Y__POS_ADJUST.get();

            renderElement(Type.MAGIC_SHIELD, x, y + leftY, mc, en, data);

            x = width / 2 + 11;
            y = height - 39 - SPACING_Y;

            renderElement(Type.MANA, x, y + rightY, mc, en, data);

            y -= SPACING_Y;

            renderElement(Type.ENERGY, x, y + rightY, mc, en, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum Type {

        MANA {
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
            public int yPosTexture() {
                return 10;
            } // if bloodmage 30
        },
        ENERGY {
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
            public int yPosTexture() {
                return 20;
            }
        },
        MAGIC_SHIELD {
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
            public int yPosTexture() {
                return 0;
            }
        };

        public abstract float getCurrent(LivingEntity en, UnitData data);

        public abstract float getMax(LivingEntity en, UnitData data);

        public abstract int yPosTexture();

    }

    public void renderElement(Type type, int x, int y, Minecraft mc, LivingEntity en, UnitData data) {

        int current = (int) type.getCurrent(en, data);
        int max = (int) type.getMax(en, data);

        if (max < 1) {
            return;
        }
        int tenth = current / 10;

        int X_SPACING = 8;

        for (int i = 0; i < 10; i++) {

            if (current > 0) {
                if (current > tenth) { // fullbar
                    blit(x, y, 0, type.yPosTexture(), 9, 9);
                    x += X_SPACING;
                } else { // half
                    blit(x, y, 10, type.yPosTexture(), 9, 9);
                    x += X_SPACING;
                }
            }

            current -= tenth;

        }

    }

}