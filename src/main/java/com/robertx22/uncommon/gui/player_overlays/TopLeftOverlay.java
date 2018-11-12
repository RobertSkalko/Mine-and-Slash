package com.robertx22.uncommon.gui.player_overlays;

import java.awt.Color;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;

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
	public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit) {
		yPos = 2;

		DrawBar(mc, gui, unit, healthtexturepath, unit.health().CurrentValue(mc.player, unit), unit.healthData().Value,
				false);
		DrawBar(mc, gui, unit, manatexturepath, unit.manaData().CurrentValue, unit.manaData().Value, false);
		DrawBar(mc, gui, unit, energytexturepath, unit.energyData().CurrentValue, unit.energyData().Value, false);
		DrawBar(mc, gui, unit, experiencetexturepath, unit.experience, unit.GetExpRequiredForLevelUp(), true);

	}

	private void DrawBar(Minecraft mc, Gui gui, Unit unit, ResourceLocation res, float current, float max,
			boolean isExp) {

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
			str = "Lvl:" + unit.level + " " + now + "/" + maximum;
		}

		mc.fontRenderer.drawStringWithShadow(str, xPos + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
				yPos + 2, Color.LIGHT_GRAY.getRGB());

		yPos += 12;
	}

}
