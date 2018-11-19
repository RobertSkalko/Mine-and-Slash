package com.libraries.rabbit.gui.component.display.tabs;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.client.renderer.GlStateManager;

public class TextTab extends Tab {

	private String lines[];

	public TextTab(int x, int y, int width, int height) {
		this(x, y, width, height, "");
	}

	public TextTab(int x, int y, int width, int height, int angle, String... lines) {
		super(x, y, width, height, angle);
		this.lines = lines;
		int tabwidth = width;
		for (String line : lines) {
			tabwidth = Math.max(TextRenderer.getFontRenderer().getStringWidth(line) + 8, tabwidth);
		}
		if ((angle == 0) || (angle == 180)) {
			this.width = tabwidth;
			this.height = 8 + (8 * lines.length);
		} else {
			this.height = tabwidth;
			this.width = 8 + (8 * lines.length);
		}
	}

	public TextTab(int x, int y, int width, int height, String... lines) {
		this(x, y, width, height, 0, lines);
	}

	public List<String> getLines() {
		return Arrays.asList(lines);
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		beginDrawingTab(mouseX, mouseY, partialTicks);
		if (!isHidden) {
			int curYPos = 3;
			if (angle == 0) {
				curYPos = y + 3;
				for (String line : lines) {
					TextRenderer.renderString(
							(int) (x + ((width / 2.0) - (TextRenderer.getFontRenderer().getStringWidth(line) / 2.0))),
							curYPos, line, Color.white);
					curYPos += 8;
				}
			} else if (angle == 180) {
				GlStateManager.translate(-width, 0, 2);
				for (String line : lines) {
					TextRenderer.renderString((int) (width / 2.0) - 1, curYPos, line, Color.white,
							TextAlignment.CENTER);
					curYPos += 8;
				}
			} else if (angle == 90) {
				GlStateManager.translate(-height, 0, 2);
				curYPos = 4;
				for (String line : lines) {
					TextRenderer.renderString((int) (height / 2.0), curYPos, line, Color.white, TextAlignment.CENTER);
					curYPos += 8;
				}
			} else {

				GlStateManager.translate(height, 0, 2);
				curYPos = 4;
				for (String line : lines) {
					TextRenderer.renderString((int) (height / 2.0), curYPos, line, Color.white, TextAlignment.CENTER);
					curYPos += 8;
				}
			}
		}
		finishDrawingTab(mouseX, mouseY, partialTicks);
		drawHoverText(mouseX, mouseY, partialTicks);
	}

	public void setText(String... lines) {
		this.lines = lines;
		int tabwidth = width;
		for (String line : lines) {
			tabwidth = Math.max(TextRenderer.getFontRenderer().getStringWidth(line) + 8, tabwidth);
		}
		width = tabwidth;
		height = 8 + (8 * lines.length);
	}
}
