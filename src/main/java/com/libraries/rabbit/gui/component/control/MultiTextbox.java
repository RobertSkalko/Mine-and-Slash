package com.libraries.rabbit.gui.component.control;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.render.Renderer;
import com.libraries.rabbit.gui.render.TextRenderer;
import com.libraries.rabbit.gui.utils.Geometry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MultiTextbox extends TextBox {

	protected ScrollBar scrollBar;

	protected int maxStringLength = 1000;

	protected int textAreaHeight;

	public MultiTextbox(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		textAreaHeight = height;
		scrollBar = new ScrollBar((getX() + getWidth()) - 5, getY(), 10, getHeight(), 20).setVisiblie(false);
	}

	public MultiTextbox(int xPos, int yPos, int width, int height, String initialText) {
		super(xPos, yPos, width, height, initialText);
		textAreaHeight = height;
		scrollBar = new ScrollBar((getX() + getWidth()) - 5, getY(), 10, getHeight(), 20).setVisiblie(false);
	}

	@Override
	protected void drawBox() {
		if (isVisible()) {
			GlStateManager.pushMatrix();
			{
				if (isBackgroundVisible()) {
					drawTextBoxBackground();
				}
				TextRenderer.getFontRenderer().setUnicodeFlag(drawUnicode);
				int color = isEnabled ? getEnabledColor() : getDisabledColor();
				boolean renderCursor = isFocused() && (((cursorCounter / 6) % 2) == 0);
				int startLine = getStartLineY();
				int maxLineAmount = (height / TextRenderer.getFontRenderer().FONT_HEIGHT) + startLine;
				List<String> lines = getLines();
				int charCount = 0;
				int lineCount = 0;
				int from = Math.min(getCursorPosition(), selectionEnd);
				int to = Math.max(getCursorPosition(), selectionEnd);
				boolean renderSelection = !getSelectedText().isEmpty();
				boolean renderSelectionLine = false;
				int maxWidth = scrollBar.isVisible() ? width - 14 : width - 4;
				for (String wholeLine : lines) {
					String line = "";
					if ((TextRenderer.getFontRenderer().getStringWidth(wholeLine) > maxWidth)
							|| ((getCursorPosition() > charCount)
									&& (getCursorPosition() < (charCount + wholeLine.length())))
							|| ((renderSelection && ((from >= charCount) && (from <= (charCount + wholeLine.length()))))
									|| ((to >= charCount) && (to <= (charCount + wholeLine.length()))))) {
						for (char c : wholeLine.toCharArray()) {
							if (TextRenderer.getFontRenderer().getStringWidth(line + c) > maxWidth) {
								if ((lineCount >= startLine) && (lineCount < maxLineAmount)) {
									TextRenderer.getFontRenderer().drawString(line, getX() + 4, getY() + 4
											+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT),
											color);
								}
								if (renderSelectionLine) {
									if (from <= (charCount - line.length())) {
										int startX = getX() + 3;
										int lineY = getY()
												+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT)
												+ 4;
										renderSelectionRect(startX, lineY,
												startX + TextRenderer.getFontRenderer().getStringWidth(line) + 2,
												lineY + TextRenderer.getFontRenderer().FONT_HEIGHT);
									} else {
										int startX = getX() + TextRenderer.getFontRenderer().getStringWidth(line) + 3;
										int lineY = getY()
												+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT)
												+ 4;
										renderSelectionRect(startX, lineY,
												startX + TextRenderer.getFontRenderer()
														.getStringWidth(line.substring(charCount - from)) + 2,
												lineY + TextRenderer.getFontRenderer().FONT_HEIGHT);
									}
								}
								line = "";
								lineCount++;
							}
							if (renderSelection) {
								if (charCount == from) {
									renderSelectionLine = true;
									int startX = getX() + TextRenderer.getFontRenderer().getStringWidth(line) + 3;
									int lineY = getY()
											+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT)
											+ 4;

									if (TextRenderer.getFontRenderer().getStringWidth(wholeLine) > maxWidth) {

									}

									if (wholeLine.contains(getSelectedText())) {
										renderSelectionLine = false;
										renderSelection = false;
										// the selection is only on this line
										renderSelectionRect(startX, lineY,
												startX + TextRenderer.getFontRenderer()
														.getStringWidth(getSelectedText()) + 2,
												lineY + TextRenderer.getFontRenderer().FONT_HEIGHT);
									}
								} else if (charCount == to) {
									renderSelectionLine = false;
									renderSelection = false;
									int startX = getX() + 3;
									int lineY = getY()
											+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT)
											+ 4;
									renderSelectionRect(startX, lineY,
											startX + TextRenderer.getFontRenderer().getStringWidth(line) + 2,
											lineY + TextRenderer.getFontRenderer().FONT_HEIGHT);
								} else if (charCount > to) {
									renderSelectionLine = false;
									renderSelection = false;
								}
							}
							if (renderCursor && (charCount == getCursorPosition()) && (lineCount >= startLine)
									&& (lineCount < maxLineAmount)) {
								int cursorX = getX() + TextRenderer.getFontRenderer().getStringWidth(line) + 3;
								int cursorY = getY()
										+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT) + 4;
								if ((getText().length() == getCursorPosition()) || (c == '\n')) {
									TextRenderer.getFontRenderer().drawString("_", cursorX, cursorY, 0xFFFFFFFF);
								} else {
									Renderer.drawRect(cursorX, cursorY, cursorX + 1, cursorY + 10, 0xFFFFFFFF);
								}
							}
							charCount++;
							line += c;
						}
					}
					if ((lineCount >= startLine) && (lineCount < maxLineAmount)) {
						TextRenderer.getFontRenderer().drawString(line, getX() + 4,
								getY() + 4 + ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT),
								color);
						if (renderCursor && (charCount == getCursorPosition())) {
							int cursorX = getX() + TextRenderer.getFontRenderer().getStringWidth(line) + 3;
							int cursorY = getY()
									+ ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT) + 4;
							if ((getText().length() == getCursorPosition()) || (getText().toCharArray()[Math
									.min(charCount, getText().toCharArray().length - 1)] == '\n')) {
								TextRenderer.getFontRenderer().drawString("_", cursorX, cursorY, 0xFFFFFFFF);
							} else {
								Renderer.drawRect(cursorX, cursorY, cursorX + 1,
										cursorY + TextRenderer.getFontRenderer().FONT_HEIGHT, 0xFFFFFFFF);
							}
						}
					}
					if (renderSelectionLine) {
						if (from <= (charCount - line.length())) {
							// render the whole line
							int startX = getX() + 3;
							int lineY = getY() + ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT)
									+ 4;
							renderSelectionRect(startX, lineY,
									startX + TextRenderer.getFontRenderer().getStringWidth(line) + 2,
									lineY + TextRenderer.getFontRenderer().FONT_HEIGHT);
						} else {
							// render from the selection over
							String substring = line.substring(line.length() - (charCount - from));
							int startX = getX()
									+ TextRenderer.getFontRenderer().getStringWidth(line.replace(substring, "")) + 3;
							int lineY = getY() + ((lineCount - startLine) * TextRenderer.getFontRenderer().FONT_HEIGHT)
									+ 4;
							renderSelectionRect(startX, lineY,
									startX + TextRenderer.getFontRenderer().getStringWidth(substring) + 2,
									lineY + TextRenderer.getFontRenderer().FONT_HEIGHT);
						}
						if (renderSelectionLine && (charCount >= to)) {
							renderSelectionLine = false;
							renderSelection = false;
						}

					}
					++lineCount;
					++charCount;
				}
				textAreaHeight = lineCount * TextRenderer.getFontRenderer().FONT_HEIGHT;
				scrollBar.setVisiblie(textAreaHeight > (height - 4));
				int scale = Geometry.computeScaleFactor();
				scrollBar.setHandleMouseWheel((textAreaHeight > (height - 4)) && isUnderMouse(Mouse.getX() / scale,
						(Minecraft.getMinecraft().displayHeight - Mouse.getY()) / scale));
				scrollBar.setScrollerSize((getScrollerSize()));
				GlStateManager.resetColor();
			}
			GlStateManager.popMatrix();
			TextRenderer.getFontRenderer().setUnicodeFlag(false);
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
		return maxStringLength;
	}

	protected int getScrollerSize() {
		return (int) (((1F * height) / textAreaHeight) * (height - 4));
	}

	public int getStartLineY() {
		if ((scrollBar != null) && scrollBar.isVisible()) {
			float scrolled = scrollBar.getScrolledAmt();
			return MathHelper.ceil((scrolled * getHeight()) / TextRenderer.getFontRenderer().FONT_HEIGHT);
		}
		return 0;
	}

	@Override
	protected boolean handleInput(char typedChar, int typedKeyIndex) {
		String originalText = getText();
		switch (typedKeyIndex) {
		case Keyboard.KEY_RETURN:
			setTextWithEvent(originalText.substring(0, getCursorPosition()) + "\n"
					+ originalText.substring(getCursorPosition()));
			setCursorPosition(getCursorPosition() + 1);
			return true;
		case Keyboard.KEY_UP: {
			List<String> lines = getLines();
			int charCount = 0;
			int lineCount = 0;
			int startLine = getStartLineY();
			int maxWidth = width - 4;
			int prevLineLength = getText().length();
			for (int i = 0; i < lines.size(); ++i) {
				String wholeLine = lines.get(i);
				String line = "";
				char[] chars = wholeLine.toCharArray();
				for (char c : chars) {
					if (TextRenderer.getFontRenderer().getStringWidth(line + c) > maxWidth) {
						line = "";
						lineCount++;
					}
					if ((charCount == getCursorPosition())) {
						setCursorPosition(Math.max(0,
								charCount - (prevLineLength < line.length() ? line.length() + 1 : prevLineLength + 1)));

						return true;
					}
					charCount++;
					line += c;
				}
				if ((lineCount >= startLine)) {
					if ((charCount == getCursorPosition())) {
						setCursorPosition(Math.max(0,
								charCount - (prevLineLength < line.length() ? line.length() + 1 : prevLineLength + 1)));
						return true;
					}
				}
				prevLineLength = wholeLine.length();
				++lineCount;
				++charCount;
			}
			return true;
		}
		case Keyboard.KEY_DOWN: {
			List<String> lines = getLines();
			int charCount = 0;
			int startLine = getStartLineY();
			int maxWidth = width - 4;
			for (int i = startLine; i < lines.size(); ++i) {
				String wholeLine = lines.get(i);
				String line = "";
				char[] chars = wholeLine.toCharArray();
				for (char c : chars) {
					if (TextRenderer.getFontRenderer().getStringWidth(line + c) > maxWidth) {
						line = "";
					}
					if ((charCount == getCursorPosition())) {
						if ((i + 1) >= lines.size()) {
							setCursorPosition(getText().length());
							return true;
						} else {
							String nextLine = lines.get(i + 1);
							if (nextLine.length() >= wholeLine.length()) {
								setCursorPosition(Math.min(getText().length(), charCount + wholeLine.length() + 1));
								return true;
							} else {
								setCursorPosition(Math.min(getText().length(),
										charCount + (wholeLine.length() - line.length()) + nextLine.length() + 1));
								return true;
							}
						}
					}
					charCount++;
					line += c;
				}
				if ((charCount == getCursorPosition())) {
					if ((i + 1) >= lines.size()) {
						setCursorPosition(getText().length());
						return true;
					} else {
						String nextLine = lines.get(i + 1);
						if (nextLine.length() >= wholeLine.length()) {
							setCursorPosition(Math.min(getText().length(), charCount + wholeLine.length() + 1));
							return true;
						} else {
							setCursorPosition(Math.min(getText().length(),
									charCount + (wholeLine.length() - line.length()) + nextLine.length() + 1));
							return true;
						}
					}
				}
				++charCount;
			}
			return true;
		}
		default:
			return super.handleInput(typedChar, typedKeyIndex);
		}
	}

	@Override
	protected boolean handleMouseClick(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isEnabled() && !overlap && isTextBoxUnderMouse(posX, posY)
				&& !scrollBar.isUnderMouse(posX, posY);
		setIsFocused(clicked);
		if (isFocused() && (mouseButtonIndex == 0)) {
			TextRenderer.getFontRenderer().setUnicodeFlag(drawUnicode);
			int length = posX - getX();
			String temp = TextRenderer.getFontRenderer()
					.trimStringToWidth(text.substring(Math.max(0, Math.min(scrollOffset, text.length()))), getWidth());
			setCursorPosition(TextRenderer.getFontRenderer().trimStringToWidth(temp, length).length()
					+ Math.max(0, Math.min(scrollOffset, text.length())));
			int x = posX - getX();
			int y = ((posY - getY() - 4) / TextRenderer.getFontRenderer().FONT_HEIGHT) + getStartLineY();
			cursorPos = 0;
			List<String> lines = getLines();
			int charCount = 0;
			int lineCount = 0;
			int maxWidth = getWidth() - 4;
			for (int i = 0; i < lines.size(); ++i) {
				String wholeLine = lines.get(i);
				String line = "";
				char[] chars = wholeLine.toCharArray();
				for (char c : chars) {
					if (TextRenderer.getFontRenderer().getStringWidth(line + c) > maxWidth) {
						lineCount++;
						line = "";
						if (y < lineCount) {
							break;
						}
					}
					if ((lineCount == y) && (x <= TextRenderer.getFontRenderer().getStringWidth(line + c))) {
						setCursorPosition(charCount);
						return clicked;
					}
					charCount++;
					line += c;
				}
				if (lineCount == y) {
					setCursorPosition(charCount);
					return clicked;
				}

				charCount++;
				lineCount++;
				if (y < lineCount) {
					break;
				}

			}
			if (y >= lineCount) {
				setCursorPosition(getText().length());
			}
			TextRenderer.getFontRenderer().setUnicodeFlag(false);
		}
		return clicked;
	}

	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		return (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
				&& (mouseY <= (getY() + getHeight()));
	}

	@Override
	public void pushText(String text) {
		String result = "";
		int i = getCursorPosition() < selectionEnd ? getCursorPosition() : selectionEnd;
		int j = getCursorPosition() < selectionEnd ? selectionEnd : getCursorPosition();
		int k = getMaxLength() - getText().length() - (i - selectionEnd);

		if (getText().length() > 0) {
			result += getText().substring(0, i);
		}
		int end = 0;
		if (k < text.length()) {
			result = result + text.substring(0, k);
			end = k;
		} else {
			result = result + text;
			end = text.length();
		}
		if ((getText().length() > 0) && (j < getText().length())) {
			result = result + getText().substring(j);
		}
		setTextWithEvent(result);
		moveCursorBy((i - selectionEnd) + end);
	}

	@Override
	public void setup() {
		scrollBar.setX((getX() + getWidth()) - (scrollBar.getWidth() / 2));
		registerComponent(scrollBar);

	}

	@Override
	public GuiWidget setX(int x) {
		super.setX(x);
		if (scrollBar != null) {
			scrollBar.setX((x + getWidth()) - (scrollBar.getWidth() / 2));
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
