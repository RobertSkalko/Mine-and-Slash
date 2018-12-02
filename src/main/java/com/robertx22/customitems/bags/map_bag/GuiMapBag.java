package com.robertx22.customitems.bags.map_bag;

import com.robertx22.mmorpg.Ref;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMapBag extends GuiContainer {

    private static int rows = 6;
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/Map_bag.png");

    public GuiMapBag(InventoryPlayer playerInv, InventoryMapBag flowerBagInv) {
	super(new ContainerMapBag(playerInv, flowerBagInv));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	this.drawDefaultBackground();
	super.drawScreen(mouseX, mouseY, partialTicks);
	this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	this.fontRenderer.drawString("Map Bag", 8, 6, 4210752);
	// this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.getTextureManager().bindTexture(texture);
	int i = (this.width - this.xSize) / 2;
	int j = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(i, j, 0, 0, this.xSize, rows * 18 + 17);
	this.drawTexturedModalRect(i, j + rows * 18 + 17, 0, 126, this.xSize, 96);
    }

}