package com.rabbit.gui.component.control;

import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextAlignment;
import com.rabbit.gui.render.TextRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Simple button component <br>
 * Supported width: <b> 0 - 400 </b> (due to texture length it can't be larger)
 * <br>
 * Supported height: <b> 5 - INFINITY </b> <br>
 *
 * Use {@link #setClickListener(ButtonClickListener)} to define action on button
 * pressed
 */

@SideOnly(Side.CLIENT)
@LayoutComponent
public class ToggleButton extends Button {

	protected boolean toggle;

	/** Dummy constructor. Used in layout */
	protected ToggleButton() {
	}

	public ToggleButton(int xPos, int yPos, int width, int height, String title, boolean toggle) {
		super(xPos, yPos, width, height, title);
		this.toggle = toggle;
	}

	public boolean getToggleState() {
		return toggle;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
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
						Renderer.drawHoveringText(hoverText, mouseX - tlineWidth - 20, mouseY + 12);
					} else {
						Renderer.drawHoveringText(hoverText, mouseX, mouseY + 12);
					}
				}
			} else {
				if (toggle) {
					drawButton(Button.IDLE_STATE);
				} else {
					drawButton(Button.DISABLED_STATE);
				}

			}
			TextRenderer.renderString(getX() + (getWidth() / 2), (getY() + (getHeight() / 2)) - 4, getText(),
					TextAlignment.CENTER);
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isButtonUnderMouse(posX, posY) && isEnabled() && !overlap;
		if (clicked) {
			if (getClickListener() != null) {
				getClickListener().onClick(this);
			}
			playClickSound();
			toggle = !toggle;
		}
		return clicked;
	}
}
