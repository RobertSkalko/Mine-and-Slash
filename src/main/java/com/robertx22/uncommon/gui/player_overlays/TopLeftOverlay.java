package com.robertx22.uncommon.gui.player_overlays;

import java.awt.Color;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class TopLeftOverlay extends BasePlayerOverlay {
    int xPos = 2;
    int yPos = 2;

    @Override
    public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit,
	    UnitData data) {
	yPos = 2;

	DrawBar(mc, gui, unit, healthtexturepath, unit.health().CurrentValue(mc.player, unit), unit.healthData().Value,
		false, data);
	DrawBar(mc, gui, unit, manatexturepath, data.getCurrentMana(), unit.manaData().Value, false, data);
	DrawBar(mc, gui, unit, energytexturepath, data.getCurrentEnergy(), unit.energyData().Value, false, data);
	DrawBar(mc, gui, unit, experiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), true, data);

    }

    private void DrawBar(Minecraft mc, Gui gui, Unit unit, ResourceLocation res, float current, float max,
	    boolean isExp, UnitData data) {

	GlStateManager.color(1F, 1F, 1F, 1F);
	mc.getTextureManager().bindTexture(res);
	gui.drawTexturedModalRect(xPos, yPos, 0, 0, TEXTURE_WIDTH, 11);
	int barwidth = (int) (((float) current / max * 100));
	gui.drawTexturedModalRect(xPos + 3, yPos + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

	String now = DamageEffect.FormatNumber((int) current);
	String maximum = DamageEffect.FormatNumber((int) max);
	String str = "";

	if (!isExp) {
	    str = now + "/" + maximum;
	} else {
	    str = "Lvl:" + data.getLevel() + " " + now + "/" + maximum;
	}

	mc.fontRenderer.drawStringWithShadow(str, xPos + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
		yPos + 2, Color.LIGHT_GRAY.getRGB());

	yPos += 12;
    }

}
