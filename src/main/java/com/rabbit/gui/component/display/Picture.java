package com.rabbit.gui.component.display;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.utils.TextureHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Picture extends GuiWidget {

	private UUID pictureUUID = UUID.randomUUID();
	private int imageWidth;
	private int imageHeight;

	public Picture(int xPos, int yPos, int width, int height, ResourceLocation texture) {
		super(xPos, yPos, width, height);
		TextureHelper.addStaticTexture(pictureUUID, texture);
		try {
			BufferedImage image = ImageIO
					.read(Minecraft.getMinecraft().getResourceManager().getResource(texture).getInputStream());
			setImageWidth(image.getWidth());
			setImageHeight(image.getHeight());
		} catch (IOException ioex) {
			ioex.printStackTrace();
			// throw new RuntimeException("Can't get resource", ioex);
		}
	}

	public Picture(int xPos, int yPos, int width, int height, String textureLocation) {
		super(xPos, yPos, width, height);
		TextureHelper.addTexture(pictureUUID, textureLocation);
		if (TextureHelper.isTextureStatic(pictureUUID)) {
			try {
				BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
						.getResource(TextureHelper.getStaticTexture(pictureUUID)).getInputStream());
				setImageWidth(image.getWidth());
				setImageHeight(image.getHeight());
			} catch (IOException ioex) {
				ioex.printStackTrace();
				// throw new RuntimeException("Can't get resource", ioex);
			}
		} else if (TextureHelper.isTextureDynamic(pictureUUID)) {
			try {
				BufferedImage image = ImageIO.read(new URL(textureLocation));
				setImageWidth(image.getWidth());
				setImageHeight(image.getHeight());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public Picture(int xPos, int yPos, int width, int height, UUID textureId) {
		super(xPos, yPos, width, height);
		pictureUUID = textureId;
		if (TextureHelper.isTextureStatic(pictureUUID)) {
			try {
				BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
						.getResource(TextureHelper.getStaticTexture(pictureUUID)).getInputStream());
				setImageWidth(image.getWidth());
				setImageHeight(image.getHeight());
			} catch (IOException ioex) {
				ioex.printStackTrace();
				// throw new RuntimeException("Can't get resource", ioex);
			}
		} else if (TextureHelper.isTextureDynamic(pictureUUID)) {
			setImageWidth(TextureHelper.getDynamicTextureInfo(textureId).getLeft());
			setImageHeight(TextureHelper.getDynamicTextureInfo(textureId).getRight());
		}

	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	@Override
	public void onDraw(int xMouse, int yMouse, float partialTicks) {
		super.onDraw(xMouse, yMouse, partialTicks);
		renderPicture();
	}

	private void renderPicture() {
		GlStateManager.pushMatrix();
		{
			GlStateManager.resetColor();
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableTexture2D();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			TextureHelper.bindTexture(pictureUUID);
			Renderer.drawScaledTexturedRect(getX(), getY(), getWidth(), getHeight());
		}
		GlStateManager.popMatrix();
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public void setImageTexture(ResourceLocation texture) {
		TextureHelper.addStaticTexture(pictureUUID, texture);
		try {
			BufferedImage image = ImageIO
					.read(Minecraft.getMinecraft().getResourceManager().getResource(texture).getInputStream());
			setImageWidth(image.getWidth());
			setImageHeight(image.getHeight());
		} catch (IOException ioex) {
			throw new RuntimeException("Can't get resource", ioex);
		}
	}

	public void setImageTexture(String textureLocation) {
		TextureHelper.addTexture(pictureUUID, textureLocation);
		if (TextureHelper.isTextureStatic(pictureUUID)) {
			try {
				BufferedImage image = ImageIO.read(Minecraft.getMinecraft().getResourceManager()
						.getResource(TextureHelper.getStaticTexture(pictureUUID)).getInputStream());
				setImageWidth(image.getWidth());
				setImageHeight(image.getHeight());
			} catch (IOException ioex) {
				throw new RuntimeException("Can't get resource", ioex);
			}
		} else if (TextureHelper.isTextureDynamic(pictureUUID)) {
			setImageWidth(TextureHelper.getDynamicTextureInfo(pictureUUID).getLeft());
			setImageHeight(TextureHelper.getDynamicTextureInfo(pictureUUID).getRight());
		}
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
}
