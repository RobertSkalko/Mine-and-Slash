package com.robertx22.uncommon.gui.player_overlays;

import com.robertx22.mmorpg.ModConfig;
import com.robertx22.mmorpg.Player_GUIs;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BarsGUI extends Gui {
	private Minecraft mc;

	BottomMiddleOverlay bottomMiddle = new BottomMiddleOverlay();
	BottomMiddleCornersOverlay bottomMiddleCorners = new BottomMiddleCornersOverlay();
	TopLeftOverlay topleft = new TopLeftOverlay();

	public BarsGUI(Minecraft mc) {
		super();
		this.mc = mc;

	}

	Unit unit;
	int ticks = 0;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {

		if (event.getType().equals(ElementType.HEALTH)) {
			event.setCanceled(true);
		}
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		ticks++;

		if (ticks > 12) {
			unit = UnitSaving.Load((EntityPlayer) mc.player);

		}

		if (unit == null) {
			return;
		}

		if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Top_Left)) {
			topleft.Draw(this, mc, mc.player, event, unit);
		} else if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Bottom_Middle)) {
			bottomMiddle.Draw(this, mc, mc.player, event, unit);
		} else if (ModConfig.Client.PLAYER_GUI_TYPE.equals(Player_GUIs.Bottom_Middle_Corners)) {
			bottomMiddleCorners.Draw(this, mc, mc.player, event, unit);
		}

	}

}