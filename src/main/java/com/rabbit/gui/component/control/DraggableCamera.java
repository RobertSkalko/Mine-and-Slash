package com.rabbit.gui.component.control;

import com.rabbit.gui.component.GuiWidget;

import net.minecraft.client.Minecraft;

public class DraggableCamera extends GuiWidget {

	private boolean isEnabled;
	private boolean isDragging;
	private int startMouseX;
	private int startMouseY;

	public DraggableCamera() {
		// TODO Auto-generated constructor stub
	}

	public DraggableCamera(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public void onDraw(int xMouse, int yMouse, float partialTicks) {
		if (isEnabled && isDragging) {
			float f = (Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6F) + 0.2F;
			float f1 = f * f * f * 16.0F;
			float f2 = (startMouseX - xMouse) * f1;
			float f3 = (startMouseY - yMouse) * f1;
			Minecraft.getMinecraft().player.turn(f2, f3 * (Minecraft.getMinecraft().gameSettings.invertMouse ? 1 : -1));
			startMouseX = xMouse;
			startMouseY = yMouse;

		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		if (isEnabled) {
			super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);

			isDragging = !overlap;

			if (isDragging) {
				startMouseX = posX;
				startMouseY = posY;
			}

			return isDragging;
		}
		return super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);
	}

	@Override
	public void onMouseRelease(int mouseX, int mouseY) {
		super.onMouseRelease(mouseX, mouseY);
		if (isEnabled) {
			if (isDragging) {
				isDragging = false;
			}
		}
	}

	public GuiWidget setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

}
