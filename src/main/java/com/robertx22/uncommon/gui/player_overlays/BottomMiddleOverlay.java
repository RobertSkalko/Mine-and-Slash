package com.robertx22.uncommon.gui.player_overlays;

import java.awt.Color;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BottomMiddleOverlay extends BasePlayerOverlay {

	@Override
	public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit) {
		// ENERGY
		int x = event.getResolution().getScaledWidth() / 2 - this.TEXTURE_WIDTH * 2;
		int y = event.getResolution().getScaledHeight() - 15;

		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(energytexturepath);
		gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
		int barwidth = (int) (((float) unit.energyData().CurrentValue / unit.energyData().Value * 100));
		gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

		String now = DamageEffect.FormatNumber((int) unit.energyData().CurrentValue);
		String maximum = DamageEffect.FormatNumber((int) unit.energyData().Value);
		String str = now + "/" + maximum;

		mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
				y + 2, Color.LIGHT_GRAY.getRGB());
		// ENERGY

		// MANA
		x = event.getResolution().getScaledWidth() / 2 + this.TEXTURE_WIDTH;
		y = event.getResolution().getScaledHeight() - 15;

		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(manatexturepath);
		gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
		barwidth = (int) (((float) unit.manaData().CurrentValue / unit.manaData().Value * 100));
		gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

		now = DamageEffect.FormatNumber((int) unit.manaData().CurrentValue);
		maximum = DamageEffect.FormatNumber((int) unit.manaData().Value);
		str = now + "/" + maximum;

		mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
				y + 2, Color.LIGHT_GRAY.getRGB());
		// MANA

		// HEALTH
		x = event.getResolution().getScaledWidth() / 2 - this.TEXTURE_WIDTH;
		y = event.getResolution().getScaledHeight() - 53;

		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(healthtexturepath);
		gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
		barwidth = (int) (((float) unit.health().CurrentValue(entity, unit) / unit.healthData().Value * 100));
		gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

		now = DamageEffect.FormatNumber((int) unit.health().CurrentValue(entity, unit));
		maximum = DamageEffect.FormatNumber((int) unit.healthData().Value);
		str = now + "/" + maximum;

		mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
				y + 2, Color.LIGHT_GRAY.getRGB());
		// HEALTH

		// EXP
		x = event.getResolution().getScaledWidth() / 2 + 5;
		y = event.getResolution().getScaledHeight() - 53;

		GlStateManager.color(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(experiencetexturepath);
		gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
		barwidth = (int) (((float) unit.experience / unit.GetExpRequiredForLevelUp() * 100));
		gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

		now = DamageEffect.FormatNumber((int) unit.experience);
		maximum = DamageEffect.FormatNumber((int) unit.GetExpRequiredForLevelUp());
		str = "Lvl:" + unit.level + " " + now + "/" + maximum;

		mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
				y + 2, Color.LIGHT_GRAY.getRGB());
		// EXP

	}

}
