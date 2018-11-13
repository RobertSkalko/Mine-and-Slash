package com.rabbit.gui.component.display;

import java.awt.Color;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Line extends GuiWidget {

	private Color color;

	@LayoutComponent
	private float lineWidth;

	@LayoutComponent
	protected int x2;

	@LayoutComponent
	protected int y2;

	public Line(int x, int y, int x2, int y2, int lineWidth, Color color) {
		super(x, y, Math.abs(x - x2), Math.abs(y - y2));
		this.lineWidth = lineWidth;
		this.color = color;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		Renderer.drawLine(x, y, x2, y2, color, lineWidth);
	}
}
