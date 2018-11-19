package com.libraries.rabbit.gui.component.display.tabs;

import java.awt.Color;
import java.util.UUID;

import com.libraries.rabbit.gui.component.display.Picture;
import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class PictureTab extends Tab {

	private String title;
	private Picture picture;

	public PictureTab(int x, int y, int width, int height, int angle, ResourceLocation texture) {
		this(x, y, width, height, "", angle, texture);
	}

	public PictureTab(int x, int y, int width, int height, int angle, String texture) {
		this(x, y, width, height, "", angle, texture);
	}

	public PictureTab(int x, int y, int width, int height, int angle, UUID texture) {
		this(x, y, width, height, "", angle, texture);
	}

	public PictureTab(int x, int y, int width, int height, ResourceLocation texture) {
		this(x, y, width, height, 0, texture);
	}

	public PictureTab(int x, int y, int width, int height, String texture) {
		this(x, y, width, height, 0, texture);
	}

	public PictureTab(int x, int y, int width, int height, String title, int angle, ResourceLocation texture) {
		super(x, y, width, height, angle);
		this.title = title;
		if ((angle % 180) == 0) {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			} else {
				double size = Math.min(width * .75, height * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			}
		} else {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			} else {
				double size = Math.min(width * .75, height * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			}
		}
	}

	public PictureTab(int x, int y, int width, int height, String title, int angle, String texture) {
		super(x, y, width, height, angle);
		this.title = title;
		if ((angle % 180) == 0) {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			} else {
				double size = Math.min(width * .75, height * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			}
		} else {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			} else {
				double size = Math.min(width * .75, height * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			}
		}
	}

	public PictureTab(int x, int y, int width, int height, String title, int angle, UUID texture) {
		super(x, y, width, height, angle);
		this.title = title;
		if ((angle % 180) == 0) {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			} else {
				double size = Math.min(width * .75, height * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			}
		} else {
			if ((title != null) && !title.isEmpty()) {
				double size = Math.min((width - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75,
						(height - (2 + TextRenderer.getFontRenderer().FONT_HEIGHT)) * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			} else {
				double size = Math.min(width * .75, height * .75);
				picture = new Picture(0, 0, (int) (size), (int) (size), texture);
			}
		}
	}

	public PictureTab(int x, int y, int width, int height, UUID texture) {
		this(x, y, width, height, 0, texture);
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		beginDrawingTab(mouseX, mouseY, partialTicks);
		if (!isHidden) {
			if (angle == 180) {
				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(width, 0, 1);
					TextRenderer.renderString((int) (width / 2.0) - 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(-picture.getWidth() / 4,
							(height / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(width - (picture.getWidth() / 4), height / 9, 1);
				}
				picture.onDraw(mouseX, mouseY, partialTicks);
			} else if (angle == 90) {
				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(-height, 0, 1);
					TextRenderer.renderString((int) (height / 2.0) + 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(picture.getWidth() / 4,
							(width / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(-height + (picture.getWidth() / 4), width / 9, 1);
				}
				picture.onDraw(mouseX, mouseY, partialTicks);
			} else if (angle == 270) {
				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(height, 0, 0);
					TextRenderer.renderString((int) (height / 2.0) - 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(-picture.getWidth() / 4,
							(width / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(height - (picture.getWidth() / 4), width / 9, 1);
				}
				picture.onDraw(mouseX, mouseY, partialTicks);
			} else {

				if ((title != null) && !title.isEmpty()) {
					GlStateManager.translate(-width, 0, 0);
					TextRenderer.renderString((int) (width / 2.0) - 2, 5, title, Color.white, TextAlignment.CENTER);
					GlStateManager.translate(picture.getWidth() / 4,
							(height / 9) + (2 + TextRenderer.getFontRenderer().FONT_HEIGHT), 0);
				} else {
					GlStateManager.translate(-width + (picture.getWidth() / 4), height / 9, 1);
				}
				picture.onDraw(mouseX, mouseY, partialTicks);
			}
		}
		finishDrawingTab(mouseX, mouseY, partialTicks);
		drawHoverText(mouseX, mouseY, partialTicks);
	}

	public void setPicture(ResourceLocation texture) {
		picture.setImageTexture(texture);
	}

	public void setPicture(String texture) {
		picture.setImageTexture(texture);
	}
}
