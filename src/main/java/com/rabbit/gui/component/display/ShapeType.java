package com.rabbit.gui.component.display;

import com.rabbit.gui.render.Renderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Contains shape forms which can be used in Shape.class component
 */

@SideOnly(Side.CLIENT)
public enum ShapeType {
	RECT((x, y, width, height, color) -> Renderer.drawRect(x, y, x + width, y + height, color)), TRIANGLE(
			(x, y, width, height, color) -> Renderer.drawTriangle(x, y + height, x + (width / 2), y, x + width,
					y + height, color)), CIRCLE((x, y, width, height, color) -> {
						int side = Math.max(width, height);
						Renderer.drawFilledArc(x + (side / 2), y + (side / 2), side / 2, 0, 360, color);
					});

	interface ShapeDrawer {
		void draw(int x, int y, int width, int height, int color);
	}

	private ShapeDrawer drawer;

	ShapeType(ShapeDrawer drawer) {
		this.drawer = drawer;
	}

	public void draw(int x, int y, int width, int height, int color) {
		drawer.draw(x, y, width, height, color);
	}
}
