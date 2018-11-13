package com.rabbit.gui.component.control;

import com.rabbit.gui.component.display.Picture;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextRenderer;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class CheckBoxPictureButton extends Button {

	@FunctionalInterface
	public static interface ButtonClickListener {
		void onClick(CheckBoxPictureButton button);
	}

	private CheckBox checkbox;
	private Picture picture;

	/** Dummy constructor. Used in layout */
	protected CheckBoxPictureButton() {
		super();
	}

	public CheckBoxPictureButton(int xPos, int yPos, int width, int height, ResourceLocation texture, boolean checked) {
		super(xPos, yPos, width, height, "");
		picture = new Picture(xPos + (width / 5), yPos + 1, (int) (width * .8) - 1, (int) (height * .8) - 1, texture);
		if (width <= height) {
			checkbox = new CheckBox(xPos + 2, (int) ((yPos + (height * .66)) - 2), height / 3, height / 3, "", checked);
		} else {
			checkbox = new CheckBox(xPos + 2, (yPos + (height / 2)) - 2, height / 2, height / 2, "", checked);
		}
		checkbox.setIsEnabled(false);
	}

	public CheckBoxPictureButton(int xPos, int yPos, int width, int height, String texture, boolean checked) {
		super(xPos, yPos, width, height, "");
		picture = new Picture(xPos + (width / 5), yPos + 1, (int) (width * .8) - 1, (int) (height * .8) - 1, texture);
		if (width <= height) {
			checkbox = new CheckBox(xPos + 2, (int) ((yPos + (height * .66)) - 2), height / 3, height / 3, "", checked);
		} else {
			checkbox = new CheckBox(xPos + 2, (yPos + (height / 2)) - 2, height / 2, height / 2, "", checked);
		}
		checkbox.setIsEnabled(false);
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
			prepareRender();
			if (!isEnabled()) {
				drawButton(Button.DISABLED_STATE);
				picture.onDraw(mouseX, mouseY, partialTicks);
				checkbox.onDraw(mouseX, mouseY, partialTicks);
			} else if (isButtonUnderMouse(mouseX, mouseY)) {
				drawButton(Button.HOVER_STATE);
				picture.onDraw(mouseX, mouseY, partialTicks);
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
				if (checkbox.isChecked()) {
					drawButton(Button.DISABLED_STATE);
				} else {
					drawButton(Button.IDLE_STATE);
				}
				picture.onDraw(mouseX, mouseY, partialTicks);
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
