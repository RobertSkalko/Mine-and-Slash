package com.rabbit.gui.component.display;

import java.awt.Color;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class HorizontalLine extends GuiWidget {

	private Color color;

	@LayoutComponent
	private float lineWidth;

	public HorizontalLine(int x, int y, int width, int lineWidth, Color color) {
		super(x, y, width, lineWidth);
		this.lineWidth = lineWidth;
		this.color = color;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		Renderer.drawLine(x, y, x + width, y, color, lineWidth);
	}
}
