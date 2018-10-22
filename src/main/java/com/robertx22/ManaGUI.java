package com.robertx22;

import org.lwjgl.opengl.GL11;

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

public class ManaGUI extends Gui {
	private Minecraft mc;
	private final ResourceLocation texturepath = new ResourceLocation("mmorpg", "textures/gui/mana_bar2.png");

	public ManaGUI(Minecraft mc) {
		super();
		// We need this to invoke the render engine.
		this.mc = mc;
	}

	int ticks = 0;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {

		if (event.isCancelable() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}

		ticks++;
		if (ticks < 5) {
			return;
		}

		Unit unit = UnitSaving.Load((EntityPlayer) mc.player);

		if (unit != null) {
			System.out.println("IT WORKS");
		}

		/*
		 * if (unit == null) { System.out.println("error"); return; }
		 */

		// Starting position for the mana bar - 2 pixels from the top left corner.
		int xPos = 2;
		int yPos = 2;

		// The center of the screen can be gotten like this during this event:
		// int xPos = event.resolution.getScaledWidth() / 2;
		// int yPos = event.resolution.getScaledHeight() / 2;

		// Be sure to offset based on your texture size or your texture will not be
		// truly centered:
		// int xPos = (event.resolution.getScaledWidth() + textureWidth) / 2;
		// int yPos = (event.resolution.getScaledHeight() + textureHeight) / 2;

		// setting all color values to 1.0F will render the texture as it appears in
		// your texture file
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		// Somewhere in Minecraft vanilla code it says to do this because of a lighting
		// bug
		GL11.glDisable(GL11.GL_LIGHTING);

		// Bind your texture to the render engine
		this.mc.getTextureManager().bindTexture(texturepath);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		/*
		 * The parameters for drawTexturedModalRect are as follows:
		 * 
		 * drawTexturedModalRect(int x, int y, int u, int v, int width, int height);
		 * 
		 * x and y are the on-screen position at which to render. u and v are the
		 * coordinates of the most upper-left pixel in your texture file from which to
		 * start drawing. width and height are how many pixels to render from the start
		 * point (u, v)
		 */
		// First draw the background layer. In my texture file, it starts at the upper-
		// left corner (x=0, y=0), is 50 pixels long and 4 pixels thick (y value)
		// this.drawTexturedModalRect(xPos, yPos, 0, 0, 50, 4);

		drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);
		int manabarwidth = (int) (((float) 5 / 100 * 49));
		drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, manabarwidth, 3);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}