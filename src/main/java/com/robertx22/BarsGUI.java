package com.robertx22;

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

	public BarsGUI(Minecraft mc) {
		super();
		// We need this to invoke the render engine.
		this.mc = mc;
	}

	int xPos = 2;
	int yPos = 2;

	int yPos2 = 15;

	int ticks = 0;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {

		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		Unit unit = UnitSaving.Load((EntityPlayer) mc.player);

		if (unit == null) {
			return;
		}

		// mana
		this.mc.getTextureManager().bindTexture(manatexturepath);
		drawTexturedModalRect(xPos, yPos, 0, 0, 107, 11);
		int manabarwidth = (int) (((float) unit.mana().GetCurrentValue() / unit.mana().Value * 100));
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 11, manabarwidth, 5);
		//
		// energy
		this.mc.getTextureManager().bindTexture(energytexturepath);
		drawTexturedModalRect(xPos, yPos2, 0, 0, 107, 11);
		int energybarwidth = (int) (((float) unit.energy().GetCurrentValue() / unit.energy().Value * 100));
		drawTexturedModalRect(xPos + 3, yPos2 + 3, 0, 11, energybarwidth, 5);
		//

	}
}