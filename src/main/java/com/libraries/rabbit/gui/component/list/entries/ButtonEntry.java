package com.libraries.rabbit.gui.component.list.entries;

import com.libraries.rabbit.gui.component.control.Button;
import com.libraries.rabbit.gui.component.list.DisplayList;
import com.libraries.rabbit.gui.render.Renderer;
import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.client.renderer.GlStateManager;

/**
 * Implementation of the ListEntry witch draws the given string in the center of
 * entry slot
 */
public class ButtonEntry extends Button implements ListEntry {

	public ButtonEntry(String text) {
		super(0, 0, 0, 0, text);
	}

	public ButtonEntry(String text, ButtonClickListener listener) {
		super(0, 0, 0, 0, text);
		super.setClickListener(listener);
	}

	@Override
	public void onClick(DisplayList list, int mouseX, int mouseY, int mouseButtonIndex) {
		if (isEnabled) {
			if (super.getClickListener() != null) {
				super.onMouseClicked(mouseX, mouseY, mouseButtonIndex, false);
			}
		} else {
			setSelected(false);
		}

	}

	@Override
	public void onDraw(DisplayList list, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		if (getX() != posX) {
			setX(posX);
		}
		if (getY() != posY) {
			setY(posY);
		}
		if (getWidth() != width) {
			setWidth(width);
		}
		if (getHeight() != height) {
			setHeight(height);
		}
		if (isVisible()) {
			GlStateManager.pushMatrix();
			{
				prepareRender();
				if (!isEnabled()) {
					drawButton(Button.DISABLED_STATE);
				} else if (isButtonUnderMouse(mouseX, mouseY)) {
					drawButton(Button.HOVER_STATE);
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
				}
				TextRenderer.renderString(getX() + (getWidth() / 2), (getY() + (getHeight() / 2)) - 4, getText(),
						TextAlignment.CENTER);
			}
			GlStateManager.popMatrix();
		}
	}

	@Override
	public ButtonEntry setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}
}