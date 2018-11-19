package com.libraries.rabbit.gui.component.control;

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
public class PictureToggleButton extends Button {

	private boolean toggle;

	private Picture onPicture;
	private Picture offPicture;

	/** Dummy constructor. Used in layout */
	public PictureToggleButton() {
		super();
	}

	public PictureToggleButton(int xPos, int yPos, int width, int height, ResourceLocation ontexture,
			ResourceLocation offtexture, boolean toggled) {
		super(xPos, yPos, width, height, "");
		toggle = toggled;
		onPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, ontexture);
		offPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, offtexture);
	}

	public PictureToggleButton(int xPos, int yPos, int width, int height, ResourceLocation ontexture, String offtexture,
			boolean toggled) {
		super(xPos, yPos, width, height, "");
		toggle = toggled;
		onPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, ontexture);
		offPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, offtexture);
	}

	public PictureToggleButton(int xPos, int yPos, int width, int height, String ontexture, ResourceLocation offtexture,
			boolean toggled) {
		super(xPos, yPos, width, height, "");
		toggle = toggled;
		onPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, ontexture);
		offPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, offtexture);
	}

	public PictureToggleButton(int xPos, int yPos, int width, int height, String ontexture, String offtexture,
			boolean toggled) {
		super(xPos, yPos, width, height, "");
		toggle = toggled;
		onPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, ontexture);
		offPicture = new Picture(xPos + 1, yPos + 1, width - 2, height - 2, offtexture);
	}

	public boolean isToggled() {
		return toggle;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
			GlStateManager.pushMatrix();
			{
				prepareRender();
				if (!isEnabled()) {
					drawButton(Button.DISABLED_STATE);
					if (toggle) {
						onPicture.onDraw(mouseX, mouseY, partialTicks);
					} else {
						offPicture.onDraw(mouseX, mouseY, partialTicks);
					}
				} else if (isButtonUnderMouse(mouseX, mouseY)) {
					drawButton(Button.HOVER_STATE);
					if (toggle) {
						onPicture.onDraw(mouseX, mouseY, partialTicks);
					} else {
						offPicture.onDraw(mouseX, mouseY, partialTicks);
					}
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
					drawButton(Button.IDLE_STATE);
					if (toggle) {
						onPicture.onDraw(mouseX, mouseY, partialTicks);
					} else {
						offPicture.onDraw(mouseX, mouseY, partialTicks);
					}
				}
				endRender();
			}
			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isButtonUnderMouse(posX, posY) && isEnabled() && !overlap;
		if (clicked) {
			if (getClickListener() != null) {
				getClickListener().onClick(this);
			}
			toggle = !toggle;
			playClickSound();
		}
		return clicked;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}
}
