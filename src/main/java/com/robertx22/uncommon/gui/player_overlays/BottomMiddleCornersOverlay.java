package com.robertx22.uncommon.gui.player_overlays;

import java.awt.Color;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class BottomMiddleCornersOverlay extends BasePlayerOverlay {

    @Override
    public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit,
	    UnitData level) {
	// ENERGY
	int x = event.getResolution().getScaledWidth() / 2 + this.TEXTURE_WIDTH - 5;
	int y = event.getResolution().getScaledHeight() - 16 - this.TEXTURE_HEIGHT;

	GlStateManager.color(1F, 1F, 1F, 1F);
	mc.getTextureManager().bindTexture(energytexturepath);
	gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
	int barwidth = (int) (((float) level.getCurrentEnergy() / unit.energyData().Value * 100));
	gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

	String now = DamageEffect.FormatNumber((int) level.getCurrentEnergy());
	String maximum = DamageEffect.FormatNumber((int) unit.energyData().Value);
	String str = now + "/" + maximum;

	mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
		y + 2, Color.LIGHT_GRAY.getRGB());
	// ENERGY

	// MANA
	x = event.getResolution().getScaledWidth() / 2 + this.TEXTURE_WIDTH - 5;
	y = event.getResolution().getScaledHeight() - 15;

	GlStateManager.color(1F, 1F, 1F, 1F);
	mc.getTextureManager().bindTexture(manatexturepath);
	gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
	barwidth = (int) (((float) level.getCurrentMana() / unit.manaData().Value * 100));
	gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

	now = DamageEffect.FormatNumber((int) level.getCurrentMana());
	maximum = DamageEffect.FormatNumber((int) unit.manaData().Value);
	str = now + "/" + maximum;

	mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
		y + 2, Color.LIGHT_GRAY.getRGB());
	// MANA

	// HEALTH
	x = event.getResolution().getScaledWidth() / 2 - this.TEXTURE_WIDTH * 2 + 5;
	y = event.getResolution().getScaledHeight() - 16 - this.TEXTURE_HEIGHT;

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
	x = event.getResolution().getScaledWidth() / 2 - this.TEXTURE_WIDTH * 2 + 5;
	y = event.getResolution().getScaledHeight() - 15;

	GlStateManager.color(1F, 1F, 1F, 1F);
	mc.getTextureManager().bindTexture(experiencetexturepath);
	gui.drawTexturedModalRect(x, y, 0, 0, TEXTURE_WIDTH, 11);
	barwidth = (int) (((float) level.getExp() / level.GetExpRequiredForLevelUp() * 100));
	gui.drawTexturedModalRect(x + 3, y + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

	now = DamageEffect.FormatNumber((int) level.getExp());
	maximum = DamageEffect.FormatNumber((int) level.GetExpRequiredForLevelUp());
	str = "Lvl:" + level.getLevel() + " " + now + "/" + maximum;

	mc.fontRenderer.drawStringWithShadow(str, x + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
		y + 2, Color.LIGHT_GRAY.getRGB());
	// EXP
    }

}
