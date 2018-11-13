package com.rabbit.gui.component.notification.types;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GenericNotification extends Gui implements INotification {
	private static final ResourceLocation guiTexture = new ResourceLocation(
			"textures/gui/achievement/achievement_background.png");
	private final ResourceLocation image;
	private final String notificationTitle;
	private final String notificationText;
	private int windowWidth;
	private int windowHeight;
	private long notificationTime;

	public GenericNotification(ResourceLocation image, String notificationTitle, String notificationText) {
		this.image = image;
		this.notificationTitle = notificationTitle;
		this.notificationText = notificationText;
		notificationTime = Minecraft.getSystemTime();
	}

	@Override
	public void drawNotification(Minecraft mc) {
		// if (notificationTime != 0L) {
		double d0 = (Minecraft.getSystemTime() - notificationTime) / 10000.0D;

		if ((d0 < 0.0D) || (d0 > 1.0D)) {
			notificationTime = 0L;
		} else {
			updateWindowScale(mc);
			GlStateManager.disableDepth();
			GlStateManager.depthMask(false);
			double d1 = d0 * 2.0D;

			if (d1 > 1.0D) {
				d1 = 2.0D - d1;
			}

			d1 *= 4.0D;
			d1 = 1.0D - d1;

			if (d1 < 0.0D) {
				d1 = 0.0D;
			}

			d1 *= d1;
			d1 *= d1;
			int i = windowWidth - 227;
			int j = 0 - (int) (d1 * 36.0D);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableTexture2D();
			mc.getTextureManager().bindTexture(GenericNotification.guiTexture);
			GlStateManager.disableLighting();
			drawScaledTexturedRectWithUV(i, j, j, 0.375, 0.7890625, 1, 0.9140625, 180, 50);

			mc.fontRenderer.drawString(notificationTitle, i + 30, j + 7, 15482167);
			// mc.fontRenderer.drawString(notificationText, i + 30, j + 19,
			// -1);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			mc.fontRenderer.drawSplitString(notificationText, i + 30, j + 17, 144, -1);

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableColorMaterial();
			GlStateManager.enableLighting();
			mc.renderEngine.bindTexture(image);
			drawScaledTexturedRect(i + 3, j + 6, 100, 24, 24);
			GlStateManager.disableLighting();
			GlStateManager.depthMask(true);
			GlStateManager.enableDepth();

		}
		// } else {
		// NotificationsManager.removeNotification(this);
		// }

	}

	private void drawScaledTexturedRect(int x, int y, int z, int width, int height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x + width, y + height, z).tex(1, 1).endVertex();
		bufferbuilder.pos(x + width, y, z).tex(1, 0).endVertex();
		bufferbuilder.pos(x, y, z).tex(0, 0).endVertex();
		bufferbuilder.pos(x, y + height, z).tex(0, 1).endVertex();
		tessellator.draw();
	}

	private void drawScaledTexturedRectWithUV(int x, int y, int z, double ustart, double vstart, double uend,
			double vend, int width, int height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x + width, y + height, z).tex(uend, vend).endVertex();
		bufferbuilder.pos(x + width, y, z).tex(uend, vstart).endVertex();
		bufferbuilder.pos(x, y, z).tex(ustart, vstart).endVertex();
		bufferbuilder.pos(x, y + height, z).tex(ustart, vend).endVertex();
		tessellator.draw();
	}

	@Override
	public void updateWindowScale(Minecraft mc) {
		GlStateManager.viewport(0, 0, mc.displayWidth, mc.displayHeight);
		GlStateManager.matrixMode(5889);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(5888);
		GlStateManager.loadIdentity();
		windowWidth = mc.displayWidth;
		windowHeight = mc.displayHeight;
		ScaledResolution scaledresolution = new ScaledResolution(mc);
		windowWidth = scaledresolution.getScaledWidth();
		windowHeight = scaledresolution.getScaledHeight();
		GlStateManager.clear(256);
		GlStateManager.matrixMode(5889);
		GlStateManager.loadIdentity();
		GlStateManager.ortho(0.0D, windowWidth, windowHeight, 0.0D, 1000.0D, 3000.0D);
		GlStateManager.matrixMode(5888);
		GlStateManager.loadIdentity();
		GlStateManager.translate(0.0F, 0.0F, -2000.0F);
	}
}
