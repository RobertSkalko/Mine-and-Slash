package com.rabbit.gui.component.control;

import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class CheckBoxButton extends Button {

	@FunctionalInterface
	public static interface ButtonClickListener {
		void onClick(CheckBoxButton button);
	}

	private CheckBox checkbox;

	/** Dummy constructor. Used in layout */
	protected CheckBoxButton() {
		super();
	}

	public CheckBoxButton(int xPos, int yPos, int width, int height, String text, boolean checked) {
		super(xPos, yPos, width, height, "");

		checkbox = new CheckBox(xPos + 2, yPos + 2, height - 4, height - 4, text, checked);
		checkbox.setIsEnabled(checked);
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
			prepareRender();
			if (!isEnabled()) {
				drawButton(Button.DISABLED_STATE);
				checkbox.onDraw(mouseX, mouseY, partialTicks);
			} else if (isButtonUnderMouse(mouseX, mouseY)) {
				drawButton(Button.HOVER_STATE);
				checkbox.onDraw(mouseX, mouseY, partialTicks);
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
				checkbox.onDraw(mouseX, mouseY, partialTicks);
			}
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isButtonUnderMouse(posX, posY) && isEnabled() && !overlap;
		if (clicked) {
			if (getClickListener() != null) {
				getClickListener().onClick(this);
			}
			checkbox.setIsChecked(!checkbox.isChecked());
			playClickSound();
		}
		return clicked;
	}

	public void setToggle(boolean toggle) {
		checkbox.setIsChecked(toggle);
	}
}
