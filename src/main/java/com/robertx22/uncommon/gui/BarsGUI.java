package com.robertx22.uncommon.gui;

import java.awt.Color;

import com.robertx22.effectdatas.DamageEffect;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
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

	public BarsGUI(Minecraft mc) {
		super();
		this.mc = mc;

	}

	int xPos = 2;
	int yPos = 2;

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

		if (ticks > 15) {
			unit = UnitSaving.Load((EntityPlayer) mc.player);

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

	int TEXTURE_WIDTH = 107;
	int TEXTURE_HEIGHT = 11;

	private void DrawBar(ResourceLocation res, float current, float max) {

		GlStateManager.color(1F, 1F, 1F, 1F);

		this.mc.getTextureManager().bindTexture(res);
		drawTexturedModalRect(xPos, yPos, 0, 0, TEXTURE_WIDTH, 11);
		int barwidth = (int) (((float) current / max * 100));
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, TEXTURE_HEIGHT, barwidth, 5);

		String now = DamageEffect.FormatNumber((int) current);
		String maximum = DamageEffect.FormatNumber((int) max);
		String str = now + "/" + maximum;

		mc.fontRenderer.drawStringWithShadow(str, xPos + TEXTURE_WIDTH / 2 - mc.fontRenderer.getStringWidth(str) / 2,
				yPos + 2, Color.LIGHT_GRAY.getRGB());

		yPos += 12;
	}

}