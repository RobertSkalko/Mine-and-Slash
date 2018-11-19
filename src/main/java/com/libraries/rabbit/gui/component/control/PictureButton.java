package com.libraries.rabbit.gui.component.control;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.component.display.Picture;
import com.libraries.rabbit.gui.layout.LayoutComponent;
import com.libraries.rabbit.gui.render.Renderer;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class PictureButton extends Button {

	private Picture picture;

	private boolean drawButton;

	/** Dummy constructor. Used in layout */
	public PictureButton() {
		super();
		drawButton = true;
	}

	public PictureButton(int xPos, int yPos, int width, int height, ResourceLocation texture) {
		super(xPos, yPos, width, height, "");
		picture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, texture);
		drawButton = true;
	}

	public PictureButton(int xPos, int yPos, int width, int height, String texture) {
		super(xPos, yPos, width, height, "");
		picture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, texture);
		drawButton = true;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
			GlStateManager.pushMatrix();
			{
				prepareRender();
				if (!isEnabled()) {
					if (drawButton) {
						drawButton(Button.DISABLED_STATE);
					}
					picture.onDraw(mouseX, mouseY, partialTicks);
				} else if (isButtonUnderMouse(mouseX, mouseY)) {
					if (drawButton) {
						drawButton(Button.HOVER_STATE);
					}
					picture.onDraw(mouseX, mouseY, partialTicks);
					if (drawHoverText) {
						verifyHoverText(mouseX, mouseY);
						if (drawToLeft) {
							int tlineWidth = 0;
							for (String line : hoverText) {
								tlineWidth = TextRenderer.getFontRenderer().getStringWidth(line) > tlineWidth
										? TextRenderer.getFontRenderer().getStringWidth(line)
										: tlineWidth;
							}
							Renderer.drawHoveringText(hoverText, mouseX - tlineWidth - 20, mouseY + 12);
						} else {
							Renderer.drawHoveringText(hoverText, mouseX, mouseY + 12);
						}
					}
				} else {
					if (drawButton) {
						drawButton(Button.IDLE_STATE);
					}
					picture.onDraw(mouseX, mouseY, partialTicks);
				}
				endRender();
			}
			GlStateManager.popMatrix();
		}
	}

	public PictureButton setDrawsButton(boolean state) {
		drawButton = state;
		return this;
	}

	@Override
	public GuiWidget setHeight(int height) {
		super.setHeight(height);
		picture.setY(height - 2);
		return this;
	}

	public PictureButton setPictureTexture(ResourceLocation res) {
		picture.setImageTexture(res);
		;
		return this;
	}

	public PictureButton setPictureTexture(String res) {
		picture.setImageTexture(res);
		;
		return this;
	}

	@Override
	public void setup() {
		registerComponent(picture);
	}

	@Override
	public GuiWidget setWidth(int width) {
		super.setWidth(width);
		picture.setX(width - 2);
		return this;
	}

	@Override
	public GuiWidget setX(int x) {
		super.setX(x);
		picture.setX(x + 1);
		return this;
	}

	@Override
	public GuiWidget setY(int y) {
		super.setY(y);
		picture.setY(y + 1);
		return this;
	}
}
