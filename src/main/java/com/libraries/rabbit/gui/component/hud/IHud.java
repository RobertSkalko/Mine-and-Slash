package com.libraries.rabbit.gui.component.hud;

import com.libraries.rabbit.gui.RabbitGui;

public interface IHud {

	public static void draw() {
		RabbitGui.logger.info("Subclass did not override super method Draw of interfrace Hud");
	}

	public static void drawScaledTexturedRect(int x, int y, int z, int width, int height) {
		RabbitGui.logger.info("Subclass did not override super method drawScaledTexturedRect of interfrace Hud");
	}

	public static void updateWindowScale() {
		RabbitGui.logger.info("Subclass did not override super method updateWindowScale of interfrace Hud");
	}
}
