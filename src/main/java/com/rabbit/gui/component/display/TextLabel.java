package com.rabbit.gui.component.display;

import java.awt.Color;
import java.util.List;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.component.Shiftable;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextAlignment;
import com.rabbit.gui.render.TextRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class TextLabel extends GuiWidget implements Shiftable {

	@LayoutComponent
	protected Color color;

	@LayoutComponent
	protected String text;

	@LayoutComponent
	protected boolean isVisible = true;

	@LayoutComponent
	protected boolean multiline = false;

	@LayoutComponent
	protected boolean drawBackground = false;

	@LayoutComponent
	protected TextAlignment alignment = TextAlignment.LEFT;

	public TextLabel(int xPos, int yPos, int width, int height, Color color, String text) {
		this(xPos, yPos, width, height, color, text, TextAlignment.LEFT);
	}

	public TextLabel(int xPos, int yPos, int width, int height, Color color, String text, TextAlignment align) {
		super(xPos, yPos, width, height);
		this.text = text;
		this.color = color;
		alignment = align;
	}

	public TextLabel(int xPos, int yPos, int width, int height, String text) {
		this(xPos, yPos, width, height, null, text, TextAlignment.LEFT);
	}

	public TextLabel(int xPos, int yPos, int width, int height, String text, TextAlignment align) {
		this(xPos, yPos, width, height, null, text, align);
	}

	public TextLabel(int xPos, int yPos, int width, String text) {
		this(xPos, yPos, width, 9, text);
	}

	protected void drawAlignedLine(int x, int y, int width, String text, TextAlignment alignment) {
		if (alignment == TextAlignment.CENTER) {
			x = x + (getWidth() / 2);
		} else if (alignment == TextAlignment.RIGHT) {
			x = x + getWidth();
		}
		if (color != null) {
			TextRenderer.renderString(x, y, text, color, alignment);
		} else {
			TextRenderer.renderString(x, y, text, alignment);
		}
	}

	private void drawBackground() {
		Renderer.drawRect(getX() - 2, getY() - 2, getX() + width + 2, getY() + height + 3, -6250336);
		Renderer.drawRect(getX() - 1, getY() - 1, getX() + width + 1, getY() + height + 2, -0xFFFFFF - 1);
	}

	protected void drawMultilined() {
		List<String> displayLines = TextRenderer.getFontRenderer().listFormattedStringToWidth(text, width);
		for (int i = 0; i < displayLines.size(); i++) {
			String displayLine = displayLines.get(i);
			int y = getY() + (i * TextRenderer.getFontRenderer().FONT_HEIGHT);
			if (y >= (getY() + height)) {
				break;
			}
			drawAlignedLine(getX(), y, getWidth(), displayLine, alignment);
		}
	}

	protected void drawOneLined() {
		String displayText = TextRenderer.getFontRenderer().trimStringToWidth(text, width);
		drawAlignedLine(getX(), getY(), getWidth(), displayText, alignment);
	}

	public Color getColor() {
		return color;
	}

	public String getText() {
		return text;
	}

	public TextAlignment getTextAlignment() {
		return alignment;
	}

	public boolean isMultilined() {
		return multiline;
	}

	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		if (isVisible()) {
			if (shouldDrawBackground()) {
				drawBackground();
			}
			if (isMultilined()) {
				drawMultilined();
			} else {
				drawOneLined();
			}
		}
	}

	public TextLabel setColor(Color color) {
		this.color = color;
		return this;
	}

	public TextLabel setDrawBackground(boolean drawBackground) {
		this.drawBackground = drawBackground;
		return this;
	}

	@Override
	public TextLabel setId(String id) {
		assignId(id);
		return this;
	}

	public TextLabel setIsVisible(boolean visible) {
		isVisible = visible;
		return this;
	}

	public TextLabel setMultilined(boolean multilined) {
		multiline = multilined;
		return this;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TextLabel setTextAlignment(TextAlignment align) {
		alignment = align;
		return this;
	}

	@Override
	public void shiftX(int x) {
		setX(getX() + x);
	}

	@Override
	public void shiftY(int y) {
		setY(getY() + y);
	}

	public boolean shouldDrawBackground() {
		return drawBackground;
	}
}
