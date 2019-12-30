package com.robertx22.uncommon.gui.player_overlays;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class AzureTopLeftOverlay extends BasePlayerOverlay {
	int xPos = 2;
	int yPos = 4;

	@Override
	public void Draw(Gui gui, Minecraft mc, EntityLivingBase entity, RenderGameOverlayEvent event, Unit unit,
			UnitData data) {

		xPos = 3;
		yPos = 1;
		DrawUI(mc, gui, unit, azureleveltexturepath, data, xPos, yPos);

		xPos = 59;
		yPos = 4;
		DrawBar(mc, gui, unit, azurehealthtexturepath, unit.health().CurrentValue(mc.player, unit), unit.healthData().Value,
				false, data, xPos, yPos);

		xPos = 59;
		yPos += 11;
		DrawBar(mc, gui, unit, azuremanatexturepath, data.getCurrentMana(), unit.manaData().Value, false, data, xPos, yPos);

		xPos = 59;
		yPos += 11;
		DrawBar(mc, gui, unit, azureenergytexturepath, data.getCurrentEnergy(), unit.energyData().Value, false, data, xPos,
				yPos);

		xPos = 59;
		yPos += 11;
		DrawBar(mc, gui, unit, azureexperiencetexturepath, data.getExp(), data.GetExpRequiredForLevelUp(), true, data, xPos,
				yPos);

	}

}