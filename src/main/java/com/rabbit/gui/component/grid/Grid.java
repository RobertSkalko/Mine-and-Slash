package com.rabbit.gui.component.grid;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.component.WidgetList;
import com.rabbit.gui.component.grid.entries.GridEntry;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.utils.Geometry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class Grid extends GuiWidget implements WidgetList<GridEntry> {

	protected boolean visibleBackground = true;

	protected boolean verticalLines = true;
	protected boolean horizontalLines = true;

	@LayoutComponent
	protected int slotHeight;

	@LayoutComponent
	protected int slotWidth;

	@LayoutComponent
	protected List<GridEntry> content;

	protected int xSlots;

	protected Grid() {
	}

	public Grid(int xPos, int yPos, int width, int height, int slotWidth, int slotHeight, List<GridEntry> content) {
		super(xPos, yPos, width, height);
		this.slotHeight = slotHeight;
		this.slotWidth = slotWidth;
		xSlots = width / slotWidth;
		this.content = content;
	}

	@Override
	public Grid add(GridEntry object) {
		content.add(object);
		return this;
	}

	@Override
	public WidgetList<GridEntry> addAll(Collection<GridEntry> values) {
		values.forEach(this::add);
		return this;
	}

	@Override
	public Grid addAll(GridEntry... objects) {
		content.addAll(Arrays.asList(objects));
		return this;
	}

	@Override
	public Grid clear() {
		content.clear();
		return this;
	}

	protected void drawGridBackground() {
		Renderer.drawRect(getX() - 1, getY() - 1, getX() + width + 1, getY() + height + 1, -6250336);
		Renderer.drawRect(getX(), getY(), getX() + width, getY() + height, -0xFFFFFF - 1);
	}

	protected void drawGridContent(int mouseX, int mouseY) {
		for (int i = 0; i < content.size(); i++) {
			GridEntry entry = content.get(i);
			int slotPosX = getX() + ((i % xSlots) * slotWidth);
			int slotPosY = getY() + ((i / xSlots) * slotHeight);
			int slotWidth = this.slotWidth;
			int slotHeight = this.slotHeight;
			entry.onDraw(this, slotPosX + 1, slotPosY + 1, slotWidth - 2, slotHeight - 2, mouseX, mouseY);
		}
	}

	public boolean drawHorizontalLines() {
		return horizontalLines;
	}

	public boolean drawVerticalLines() {
		return verticalLines;
	}

	@Override
	public List<GridEntry> getContent() {
		return content;
	}

	protected void handleMouseClickGrid(int mouseX, int mouseY) {
		for (int i = 0; i < content.size(); i++) {
			GridEntry entry = content.get(i);
			int slotPosX = getX() + ((i % xSlots) * slotWidth);
			int slotPosY = getY() + ((i / xSlots) * slotHeight);
			int slotWidth = width;
			int slotHeight = this.slotHeight;
			boolean clickedOnEntry = Geometry.isDotInArea(slotPosX, slotPosY, slotWidth, slotHeight, mouseX, mouseY);
			if (clickedOnEntry) {
				entry.onClick(this, mouseX, mouseY);
			}
		}
	}

	public boolean isVisibleBackground() {
		return visibleBackground;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisibleBackground()) {
			drawGridBackground();
		}
		drawGridContent(mouseX, mouseY);
		super.onDraw(mouseX, mouseY, partialTicks);
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);
		boolean clickedOnGrid = !overlap && Geometry.isDotInArea(getX(), getY(), width, height, posX, posY);
		if (clickedOnGrid) {
			handleMouseClickGrid(posX, posY);
		}
		return clickedOnGrid;
	}

	@Override
	public Grid remove(GridEntry object) {
		content.remove(object);
		return this;
	}

	public Grid setDrawHorizontalLines(boolean flag) {
		horizontalLines = flag;
		return this;
	}

	public Grid setDrawVerticalLines(boolean flag) {
		verticalLines = flag;
		return this;
	}

	@Override
	public Grid setId(String id) {
		assignId(id);
		return this;
	}

	public Grid setVisibleBackground(boolean visibleBackground) {
		this.visibleBackground = visibleBackground;
		return this;
	}
}
