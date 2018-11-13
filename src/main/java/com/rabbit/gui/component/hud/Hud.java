package com.rabbit.gui.component.hud;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public abstract class Hud implements IHud {

	protected static final Minecraft mc = Minecraft.getMinecraft();

	protected static int windowWidth;
	protected static int windowHeight;

	protected static void drawScaledTexturedRect(int x, int y, int z, int width, int height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x + width, y + height, z).tex(1, 1).endVertex();
		bufferbuilder.pos(x + width, y, z).tex(1, 0).endVertex();
		bufferbuilder.pos(x, y, z).tex(0, 0).endVertex();
		bufferbuilder.pos(x, y + height, z).tex(0, 1).endVertex();
		tessellator.draw();
	}

	protected static void updateWindowScale() {
		GlStateManager.viewport(0, 0, Hud.mc.displayWidth, Hud.mc.displayHeight);
		GlStateManager.matrixMode(5889);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(5888);
		GlStateManager.loadIdentity();
		Hud.windowWidth = Hud.mc.displayWidth;
		Hud.windowHeight = Hud.mc.displayHeight;
		ScaledResolution scaledresolution = new ScaledResolution(Hud.mc);
		Hud.windowWidth = scaledresolution.getScaledWidth();
		Hud.windowHeight = scaledresolution.getScaledHeight();
		GlStateManager.clear(256);
		GlStateManager.matrixMode(5889);
		GlStateManager.loadIdentity();
		GlStateManager.ortho(0.0D, Hud.windowWidth, Hud.windowHeight, 0.0D, 1000.0D, 3000.0D);
		GlStateManager.matrixMode(5888);
		GlStateManager.loadIdentity();
		GlStateManager.translate(0.0F, 0.0F, -2000.0F);
	}

}
