package com.rabbit.gui.component.grid.entries;

import java.util.List;
import java.util.UUID;

import com.rabbit.gui.component.control.Button;
import com.rabbit.gui.component.display.Picture;
import com.rabbit.gui.component.grid.Grid;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextRenderer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Implementation of the ListEntry witch draws the given string in the center of
 * entry slot
 */
@SideOnly(Side.CLIENT)
public class PictureButtonGridEntry extends Button implements GridEntry {

	public static interface OnClickListener {
		void onClick(PictureButtonGridEntry entry, Grid grid, int mouseX, int mouseY);
	}

	/**
	 * Listener which would be called when user click the entry
	 */
	private OnClickListener listener;
	private Picture picture;
	private int imageWidth;

	private int imageHeight;

	public PictureButtonGridEntry(int width, int height, ResourceLocation texture) {
		this(width, height, texture, null);
	}

	public PictureButtonGridEntry(int width, int height, ResourceLocation texture, OnClickListener listener) {
		super(0, 0, width, height, "");
		picture = new Picture(0, 0, width - 2, height - 2, texture);
		this.listener = listener;
	}

	public PictureButtonGridEntry(int width, int height, String texture) {
		this(width, height, texture, null);
	}

	public PictureButtonGridEntry(int width, int height, String texture, OnClickListener listener) {
		super(0, 0, width, height, "");
		picture = new Picture(0, 0, width - 2, height - 2, texture);
		this.listener = listener;
	}

	public PictureButtonGridEntry(int width, int height, UUID textureId) {
		this(width, height, textureId, null);
	}

	public PictureButtonGridEntry(int width, int height, UUID textureId, OnClickListener listener) {
		super(0, 0, width, height, "");
		picture = new Picture(0, 0, width - 2, height - 2, textureId);
		this.listener = listener;
	}

	@Override
	public PictureButtonGridEntry addHoverText(String text) {
		originalHoverText.add(text);
		return this;
	}

	@Override
	public List<String> getHoverText() {
		return originalHoverText;
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
		if (getX() != posX) {
			setX(posX);
			picture.setX(posX + 1);
		}
		if (getY() != posY) {
			setY(posY);
			picture.setY(posY + 1);
		}
		if (getWidth() != width) {
			setWidth(width);
			picture.setWidth(width - 2);
		}
		if (getHeight() != height) {
			setHeight(height);
			picture.setHeight(height - 2);
		}
		if (isVisible()) {
			prepareRender();
			if (!isEnabled()) {
				drawButton(Button.DISABLED_STATE);
				picture.onDraw(mouseX, mouseY, 0);
			} else if (isButtonUnderMouse(mouseX, mouseY)) {
				drawButton(Button.HOVER_STATE);
				picture.onDraw(mouseX, mouseY, 0);
				if (drawHoverText) {
					verifyHoverText(mouseX, mouseY);
					if (drawToLeft) {
						int tlineWidth = 0;
						for (String line : hoverText) {
							tlineWidth = TextRenderer.getFontRenderer().getStringWidth(line) > tlineWidth
									? TextRenderer.getFontRenderer().getStringWidth(line)
									: tlineWidth;
						}
						Renderer.drawHoveringTextInScissoredArea(hoverText, mouseX - tlineWidth - 20, mouseY);
					} else {
						Renderer.drawHoveringTextInScissoredArea(hoverText, mouseX, mouseY);
					}
				}
			} else {
				drawButton(Button.IDLE_STATE);
				picture.onDraw(mouseX, mouseY, 0);
			}
		}
	}

	public PictureButtonGridEntry setClickListener(OnClickListener onClicked) {
		listener = onClicked;
		return this;
	}

	@Override
	public PictureButtonGridEntry setDoesDrawHoverText(boolean state) {
		drawHoverText = state;
		return this;
	}

	@Override
	public PictureButtonGridEntry setHoverText(List<String> text) {
		originalHoverText = text;
		return this;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public PictureButtonGridEntry setPictureTexture(ResourceLocation res) {
		picture.setImageTexture(res);
		;
		return this;
	}

	public PictureButtonGridEntry setPictureTexture(String res) {
		picture.setImageTexture(res);
		;
		return this;
	}
}
