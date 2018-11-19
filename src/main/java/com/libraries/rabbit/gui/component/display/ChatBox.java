package com.libraries.rabbit.gui.component.display;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.component.control.ScrollBar;
import com.libraries.rabbit.gui.component.control.TextBox;
import com.libraries.rabbit.gui.render.Renderer;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ChatBox extends TextBox {

	private ScrollBar scrollBar;

	protected int maxStringLenght = 1000;

	private int listHeight;

	public ChatBox(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		listHeight = height;
	}

	public ChatBox(int xPos, int yPos, int width, int height, String initialText) {
		super(xPos, yPos, width, height, initialText);
		listHeight = height;
	}

	@Override
	protected void drawBox() {
		if (isVisible()) {
			GlStateManager.pushMatrix();
			{
				int startLine = getStartLineY();
				int maxLineAmount = (height / TextRenderer.getFontRenderer().FONT_HEIGHT) + startLine;
				List<String> lines = getLines();
				List<String> newlines = new ArrayList<>();
				int lineCount = 0;
				int maxWidth = width - 10;
				for (int i = 0; i < lines.size(); ++i) {
					String wholeLine = lines.get(i);
					String line = "";
					char[] chars = wholeLine.toCharArray();
					for (char c : chars) {
						if (TextRenderer.getFontRenderer().getStringWidth(line + c) > maxWidth) {
							if ((lineCount >= startLine) && (lineCount < maxLineAmount)) {
								newlines.add(line.substring(0, line.lastIndexOf(' ')));
							}
							if (line.contains(" ")) {
								line = line.substring(line.lastIndexOf(' ') + 1);
							} else {
								line = "";
							}
							lineCount++;
						}
						line += c;
					}
					if ((lineCount >= startLine) && (lineCount < maxLineAmount)) {
						newlines.add(line);
					}
					++lineCount;
				}
				listHeight = 5 + (lineCount * TextRenderer.getFontRenderer().FONT_HEIGHT);

				Renderer.renderChatBubble(x, y, width, Math.min(height, listHeight), newlines);

				scrollBar.setVisiblie(listHeight > (height - 4));
				scrollBar.setHandleMouseWheel((listHeight > (height - 4)) && isUnderMouse(Mouse.getX(), Mouse.getY()));
				scrollBar.setScrollerSize((getScrollerSize()));
				GlStateManager.resetColor();
			}
			GlStateManager.popMatrix();
		}
	}

	public List<String> getLines() {
		List<String> lines = new ArrayList<>();
		StringBuffer currentLine = new StringBuffer();
		char[] chars = getText().toCharArray();
		for (char symbol : chars) {
			if ((symbol == '\r') || (symbol == '\n')) {
				lines.add(currentLine.toString());
				currentLine.delete(0, currentLine.length());
			} else {
				currentLine.append(symbol);
			}
		}
		lines.add(currentLine.toString());
		return lines;
	}

	@Override
	public int getMaxLength() {
		return maxStringLenght;
	}

	private int getScrollerSize() {
		return (int) (((1F * height) / listHeight) * (height - 4));
	}

	private int getStartLineY() {
		if (scrollBar != null) {
			float scrolled = scrollBar.getScrolledAmt();
			return MathHelper.ceil((scrolled * getHeight()) / TextRenderer.getFontRenderer().FONT_HEIGHT);
		}
		return 0;
	}

	@Override
	protected boolean handleMouseClick(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		return false;
	}

	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		return (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
				&& (mouseY <= (getY() + getHeight()));
	}

	@Override
	public void setup() {
		registerComponent(
				scrollBar = new ScrollBar(getX() + getWidth(), getY(), 15, getHeight(), 20).setVisiblie(false));
	}

	@Override
	public GuiWidget setX(int x) {
		super.setX(x);
		if (scrollBar != null) {
			scrollBar.setX(x + width);
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
