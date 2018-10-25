package com.robertx22.uncommon;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BarsGUI extends Gui {
	private Minecraft mc;
	private final ResourceLocation manatexturepath = new ResourceLocation("mmorpg", "textures/gui/mana_bar.png");
	private final ResourceLocation energytexturepath = new ResourceLocation("mmorpg", "textures/gui/energy_bar.png");
	private final ResourceLocation healthtexturepath = new ResourceLocation("mmorpg", "textures/gui/health_bar.png");
	private final ResourceLocation experiencetexturepath = new ResourceLocation("mmorpg",
			"textures/gui/experience_bar.png");

	public static boolean Updated = true;

	public BarsGUI(Minecraft mc) {
		super();
		// We need this to invoke the render engine.
		this.mc = mc;
	}

	int xPos = 2;

	int yPos = 2;

//	int ticks = 0;

	Unit unit;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {

		if (event.getType().equals(ElementType.HEALTH)) {
			event.setCanceled(true);
		}
		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		if (Updated) {
			unit = UnitSaving.Load((EntityPlayer) mc.player);
			Updated = false;
		}

		if (unit == null) {
			return;
		}

		yPos = 2;

		DrawBar(healthtexturepath, unit.health().CurrentValue(mc.player, unit), unit.health().Value);
		DrawBar(manatexturepath, unit.mana().GetCurrentValue(), unit.mana().Value);
		DrawBar(energytexturepath, unit.energy().GetCurrentValue(), unit.energy().Value);
		DrawBar(experiencetexturepath, unit.experience, unit.GetExpRequiredForLevelUp());

	}

	private void DrawBar(ResourceLocation res, float current, float max) {
		this.mc.getTextureManager().bindTexture(res);
		drawTexturedModalRect(xPos, yPos, 0, 0, 107, 11);
		int barwidth = (int) (((float) current / max * 100));
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 11, barwidth, 5);

		yPos += 12;
	}

}