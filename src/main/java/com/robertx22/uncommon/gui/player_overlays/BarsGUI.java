package com.robertx22.uncommon.gui.player_overlays;

import com.robertx22.config.ModConfig;
import com.robertx22.mmorpg.Player_GUIs;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BarsGUI extends Gui {
	private Minecraft mc;

	BottomMiddleOverlay bottomMiddle = new BottomMiddleOverlay();
	BottomMiddleCornersOverlay bottomMiddleCorners = new BottomMiddleCornersOverlay();
	TopLeftOverlay topleft = new TopLeftOverlay();
	MiddleOverlay middle = new MiddleOverlay();
	AzureTopLeftOverlay azuretopleft = new AzureTopLeftOverlay();

	public BarsGUI(Minecraft mc) {
		super();
		this.mc = mc;

	}

	Unit unit;
	UnitData data;
	int ticks = 0;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderPlayerOverlay(RenderGameOverlayEvent event) {

		try {

			if (event.getType().equals(ElementType.HEALTH)) {
				event.setCanceled(true);
			}
			if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
				return;
			}

			ticks++;

			if (ticks > 12) {
				UnitData newData = mc.player.getCapability(EntityData.Data, null);

				ticks = 0;

				Unit newUnit = null;
				if (newData != null) {
					newUnit = newData.getUnit();
				}

				if (newUnit != null) {
					unit = newUnit;
				}
				if (newData != null) {
					data = newData;
				}
			}

			if (unit == null || data == null || mc == null || mc.player == null) {
				return;
			}
			if (unit.energyData() == null || unit.manaData() == null || unit.healthData() == null) {
				return;
			}

			if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Top_Left)) {
				topleft.Draw(this, mc, mc.player, event, unit, data);
			} else if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Bottom_Middle)) {
				bottomMiddle.Draw(this, mc, mc.player, event, unit, data);
			} else if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Bottom_Middle_Corners)) {
				bottomMiddleCorners.Draw(this, mc, mc.player, event, unit, data);
			} else if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Middle)) {
				middle.Draw(this, mc, mc.player, event, unit, data);
			} else if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Azure_Top_Left)) {
				azuretopleft.Draw(this, mc, mc.player, event, unit, data);
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

}