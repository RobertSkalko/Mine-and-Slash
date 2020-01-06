package com.robertx22.mine_and_slash.gui.player_overlays;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import java.awt.*;

public class AzureTopLeftOverlay {
    int xPos = 2;
    int yPos = 4;

    public final ResourceLocation azuremanatexturepath = new ResourceLocation("mmorpg", "textures/gui/overlay/mana_bar_azure.png");
    public final ResourceLocation azureenergytexturepath = new ResourceLocation("mmorpg", "textures/gui/overlay/energy_bar_azure.png");
    public final ResourceLocation azurehealthtexturepath = new ResourceLocation("mmorpg", "textures/gui/overlay/health_bar_azure.png");
    public final ResourceLocation azureexperiencetexturepath = new ResourceLocation("mmorpg", "textures/gui/overlay/exp_bar_azure.png");
    public final ResourceLocation azurehudtexturepath = new ResourceLocation("mmorpg", "textures/gui/overlay/level_ui_azure.png");
    public final ResourceLocation magicshieldpath = new ResourceLocation("mmorpg", "textures/gui/overlay/magic_shield_bar.png");
    public final ResourceLocation bloodpath = new ResourceLocation("mmorpg", "textures/gui/overlay/blood_bar.png");

    public int TEXTURE_WIDTH = 106;
    public int TEXTURE_HEIGHT = 11;

    public enum Type {
        HP,
        MANA,
        ENE,
        EXP,
        LVL
    }

    public void DrawBar(Minecraft mc, AbstractGui gui, ResourceLocation res,
                        float current, float max, Type type, UnitData data, int x,
                        int y) {

        GlStateManager.color4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(res);

        if (type == Type.MANA && data.getUnit().isBloodMage()) {
            mc.getTextureManager().bindTexture(bloodpath);
            current = data.getResources().getBlood();
            max = data.getUnit().getMaximumBlood();
        }

        gui.blit(x, y, 0, 0, TEXTURE_WIDTH, this.TEXTURE_HEIGHT); // the bar

        int barwidth = MathHelper.clamp((int) (((float) current / max * 100)), 0, 100);

        gui.blit(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5); // inner fill texture

        // RENDER ENERGY SHIELD LIKE
        if (type == Type.HP) {

            float curMS = data.getResources().getMagicShield();
            if (curMS > 0) {

                float hp = data.getUnit().healthData().Value;

                float maxperc = MathHelper.clamp(curMS / hp, 0, 1);

                int enebar = (int) ((int) ((curMS / data.getUnit()
                        .magicShieldData().Value * 100)) * maxperc);
                mc.getTextureManager().bindTexture(magicshieldpath);
                gui.blit(x + 3, y + 3, 0, TEXTURE_HEIGHT, enebar, 5);
            }

        }
        // RENDER ENERGY SHIELD LIKE

        String now = NumberUtils.formatNumber((int) current);
        String maximum = NumberUtils.formatNumber((int) max);
        String str = "";

        if (type == Type.HP) { // show effective health only on numbers, but let bars each separate from hp and
            // ms
            now = NumberUtils.formatNumber((int) data.getUnit()
                    .getCurrentEffectiveHealth(mc.player, data));
            maximum = NumberUtils.formatNumber((int) data.getUnit()
                    .getMaxEffectiveHealth());
        }

        str = now + "/" + maximum;

        float text_x = x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2;
        float text_y = y + (float) TEXTURE_HEIGHT / 2 - (float) mc.fontRenderer.FONT_HEIGHT / 2 + 0.5F;

        mc.fontRenderer.drawStringWithShadow(str, text_x, text_y, Color.LIGHT_GRAY.getRGB());

    }

    public int TEXTURE_WIDTH2 = 120;
    public int TEXTURE_HEIGHT2 = 90;

    public void DrawUI(Minecraft mc, AbstractGui gui, ResourceLocation res, Type type,
                       UnitData data, int x, int y) {

        GlStateManager.color4f(1F, 1F, 1F, 1F);
        mc.getTextureManager().bindTexture(res);

        gui.blit(x, y, 0, 0, this.TEXTURE_WIDTH2, this.TEXTURE_HEIGHT2);

        String str2 = "";

        str2 = "" + data.getLevel();

        mc.fontRenderer.drawString(str2, 20, 24, Color.WHITE.getRGB());

    }

    public void Draw(AbstractGui gui, Minecraft mc, LivingEntity entity,
                     RenderGameOverlayEvent event, UnitData data) {

        Unit unit = data.getUnit();

        yPos = 2;

        float scale = 1F;

        GlStateManager.scalef(scale, scale, scale);

        xPos = 3;
        yPos = 1;
        DrawUI(mc, gui, azurehudtexturepath, Type.LVL, data, xPos, yPos);

        xPos = 59;
        yPos = 4;
        DrawBar(mc, gui, azurehealthtexturepath, unit.health()
                .CurrentValue(mc.player, unit), unit.healthData().Value, Type.HP, data, xPos, yPos);
        xPos = 59;
        yPos += 11;

        DrawBar(mc, gui, azuremanatexturepath, data.getCurrentMana(), unit.manaData().Value, Type.MANA, data, xPos, yPos);
        xPos = 59;
        yPos += 11;
        DrawBar(mc, gui, azureenergytexturepath, data.getCurrentEnergy(), unit.energyData().Value, Type.ENE, data, xPos, yPos);
        xPos = 59;
        yPos += 11;
        DrawBar(mc, gui, azureexperiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), Type.EXP, data, xPos, yPos);

        scale = 1 / scale;
        GlStateManager.scalef(scale, scale, scale);

    }

}
