package com.rabbit.gui.component.display;

import java.awt.Color;

import com.rabbit.gui.component.GuiWidget;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Shape extends GuiWidget {

	private ShapeType shapeType;
	private Color color;

	public Shape(int x, int y, int width, int height, ShapeType type, Color color) {
		super(x, y, width, height);
		shapeType = type;
		this.color = color;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		shapeType.draw(x, y, width, height, color.getRGB());
	}
}
