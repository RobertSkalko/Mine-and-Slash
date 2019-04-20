package com.robertx22.advanced_blocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public abstract class TileGui extends GuiContainer {

    public TileGui(Container inventorySlotsIn) {
	super(inventorySlotsIn);

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

	this.drawDefaultBackground();
	super.drawScreen(mouseX, mouseY, partialTicks);
	this.renderHoveredToolTip(mouseX, mouseY);
    }
}
