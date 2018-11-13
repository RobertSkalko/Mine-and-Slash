package com.rabbit.gui.component.grid.entries;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.component.grid.Grid;
import com.rabbit.gui.render.Renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Implementation of the ListEntry witch draws the given string in the center of
 * entry slot
 */
@SideOnly(Side.CLIENT)
public class PictureGridEntry implements GridEntry {

	public static interface OnClickListener {
		void onClick(PictureGridEntry entry, Grid grid, int mouseX, int mouseY);
	}

	/**
	 * Listener which would be called when user click the entry
	 */
	private OnClickListener listener;
	private ResourceLocation texture;
	private int imageWidth;

	private int imageHeight;

	public PictureGridEntry(int width, int height, ResourceLocation texture) {
		this(width, height, texture, null);
	}

	public PictureGridEntry(int width, int height, ResourceLocation texture, OnClickListener listener) {
		this.texture = texture;
		try {
			BufferedImage image = ImageIO
					.read(Minecraft.getMinecraft().getResourceManager().getResource(texture).getInputStream());
			setImageWidth(image.getWidth());
			setImageHeight(image.getHeight());
		} catch (IOException ioex) {
			throw new RuntimeException("Can't get resource", ioex);
		}
		this.listener = listener;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	@Override
	public void onClick(Grid grid, int mouseX, int mouseY) {
		if (listener != null) {
			listener.onClick(this, grid, mouseX, mouseY);
		}
	}

	@Override
	public void onDraw(Grid grid, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableTexture2D();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			Renderer.drawScaledTexturedRect(posX, posY, width, height);
		}
		GlStateManager.popMatrix();
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
}
