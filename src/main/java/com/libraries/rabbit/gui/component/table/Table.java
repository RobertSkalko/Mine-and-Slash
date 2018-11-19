package com.libraries.rabbit.gui.component.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.render.Renderer;
import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Table extends GuiWidget {

	protected List<Row> rows;

	protected boolean isVisible = true;
	protected boolean isEnabled = true;

	protected boolean verticalLines = true;
	protected boolean horizontalLines = true;

	protected boolean drawBackground = true;

	public Table(int xPos, int yPos, int width, int height, Row... rows) {
		super(xPos, yPos, width, height);
		this.rows = new ArrayList<>(Arrays.asList(rows));
	}

	public Table addRow(Row row) {
		rows.add(row);
		return this;
	}

	private void drawBackground() {
		Renderer.drawRect(getX() - 1, getY() - 1, getX() + getWidth() + 1, getY() + getHeight() + 1, -6250336);
		Renderer.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), -16777216);
	}

	public boolean drawHorizontalLines() {
		return horizontalLines;
	}

	private void drawRow(int xPos, int yPos, int width, int height, int oneLineHeight, Row row) {
		TextRenderer.renderString(xPos + (width / 2), yPos + 5, TextFormatting.UNDERLINE + row.getName(),
				TextAlignment.CENTER);
		List<String> lines = row.getStringContent();
		for (int i = 0; i < row.getContent().size(); i++) {
			TextRenderer.renderString(xPos + (width / 2), yPos + (oneLineHeight / 2) + (oneLineHeight * i),
					lines.get(i), TextAlignment.CENTER);
			if (((i + 1) != row.getContent().size()) && drawHorizontalLines()) {
				Renderer.drawRect(xPos + 5, yPos + (oneLineHeight * i) + oneLineHeight, (xPos + width) - 5,
						yPos + (oneLineHeight * i) + oneLineHeight + 1, -6250336);
			}
		}
	}

	public boolean drawVerticalLines() {
		return verticalLines;
	}

	private Row getLongestRow() {
		Row row = null;
		for (Row r : getRows()) {
			if ((row == null) || (row.getContent().size() < r.getContent().size())) {
				row = r;
			}
		}
		return row;
	}

	public List<Row> getRows() {
		return rows;
	}

	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
			if (shouldDrawBackground()) {
				drawBackground();
			}
			int oneRowWidth = getWidth() / getRows().size();
			int oneLineHeight = getHeight() / getLongestRow().getContent().size();
			for (int i = 0; i < getRows().size(); i++) {
				drawRow(getX() + (oneRowWidth * i), getY(), oneRowWidth, getHeight(), oneLineHeight, getRows().get(i));
				if (((i + 1) != getRows().size()) && drawVerticalLines()) {
					Renderer.drawRect((getX() + (oneRowWidth * i) + oneRowWidth) - 1, getY() + 5,
							getX() + (oneRowWidth * i) + oneRowWidth, (getY() + getHeight()) - 5, -6250336);
				}
			}
		}
	}

	public Table setDrawBackground(boolean flag) {
		drawBackground = flag;
		return this;
	}

	public Table setDrawHorizontalLines(boolean flag) {
		horizontalLines = flag;
		return this;
	}

	public Table setDrawVerticalLines(boolean flag) {
		verticalLines = flag;
		return this;
	}

	@Override
	public Table setId(String id) {
		assignId(id);
		return this;
	}

	public Table setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public boolean shouldDrawBackground() {
		return drawBackground;
	}

}
