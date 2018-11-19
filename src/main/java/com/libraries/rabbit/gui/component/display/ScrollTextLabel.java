package com.libraries.rabbit.gui.component.display;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.component.control.ScrollBar;
import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;
import com.libraries.rabbit.gui.utils.Geometry;

import net.minecraft.client.Minecraft;

public class ScrollTextLabel extends TextLabel {

	private ScrollBar scrollBar;

	public ScrollTextLabel(int xPos, int yPos, int width, int height, String text) {
		super(xPos, yPos, width, height, text);
	}

	public ScrollTextLabel(int xPos, int yPos, int width, int height, String text, TextAlignment align) {
		super(xPos, yPos, width, height, text, align);
	}

	public ScrollTextLabel(int xPos, int yPos, int width, String text) {
		super(xPos, yPos, width, text);
	}

	/**
	 * Evaluates if current content of textlabel can fit into it's height <br/>
	 * Used to determine if scrollbar should be vissble
	 */
	private boolean canFit() {
		int content = getLines().size() * 10;
		return content < height; // 10 - height of one symbol
	}

	@Override
	protected void drawMultilined() {
		scrollBar.setVisiblie(!canFit());
		int scale = Geometry.computeScaleFactor();
		scrollBar.setHandleMouseWheel(!canFit()
				&& isUnderMouse(Mouse.getX() / scale, (Minecraft.getMinecraft().displayHeight - Mouse.getY()) / scale));
		List<String> displayLines = getLines();
		int scrollerSize = Math.min(Math.max(height / (1 + getLines().size()), 10), height - 4);
		scrollBar.setScrollerSize(scrollerSize);

		for (int i = 0; i < displayLines.size(); i++) {
			String line = displayLines.get(i);
			int lineY = ((getY() + (i * 10)) - (int) ((10 * scrollBar.getProgress() * displayLines.size())
					- (((height - 10) * (scrollBar.getProgress())) / 1)));
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
			GL11.glScissor(getX() * scale, Minecraft.getMinecraft().displayHeight - ((getY() + getHeight()) * scale),
					getWidth() * scale, getHeight() * scale);
			drawAlignedLine(getX(), lineY, getWidth() - 10, line, alignment);
			GL11.glDisable(GL11.GL_SCISSOR_TEST);
		}

	}

	private List<String> getLines() {
		return TextRenderer.getFontRenderer().listFormattedStringToWidth(getText(), width - 10);
	}

	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		return (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
				&& (mouseY <= (getY() + getHeight()));
	}

	public GuiWidget setScrolledAmount(float amt) {
		if (scrollBar != null) {
			scrollBar.setScrolledAmt(amt);
		}
		return this;
	}

	@Override
	public void setup() {
		super.setup();
		if (isMultilined()) {
			int scrollerSize = Math.min(Math.max(height / (1 + getLines().size()), 10), height - 4);
			scrollBar = new ScrollBar((getX() + width) - 10, getY(), 10, height, scrollerSize)
					.setHandleMouseWheel(false);
			registerComponent(scrollBar);
		}
	}

	@Override
	public GuiWidget setX(int x) {
		super.setX(x);
		if (scrollBar != null) {
			scrollBar.setX((x + width) - 10);
		}
		return this;
	}

	@Override
	public GuiWidget setY(int y) {
		super.setY(y);
		if (scrollBar != null) {
			scrollBar.setY(y);
		}
		return this;
	}
}
