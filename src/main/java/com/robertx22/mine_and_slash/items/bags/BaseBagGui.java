package com.robertx22.mine_and_slash.items.bags;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public abstract class BaseBagGui<T extends Container> extends ContainerScreen<T> {

    public BaseBagGui(PlayerInventory inv, T inventorySlotsIn) {
        super(inventorySlotsIn, inv, new StringTextComponent(""));

        this.xSize = BaseBagGui.bagXSize;
        this.ySize = BaseBagGui.bagYSize;
    }

    public BaseBagGui(PlayerInventory inv, T inventorySlotsIn, int sizex, int sizey) {
        super(inventorySlotsIn, inv, new StringTextComponent(""));

        this.xSize = sizex;
        this.ySize = sizey;
    }

    public static final int bagXSize = 176;
    public static final int bagYSize = 207;

    public abstract ResourceLocation texture();

    public abstract int rows();

    public abstract String name();

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();

        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(name(), 8, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX,
                                                   int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(texture());
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.blit(i, j, 0, 0, this.xSize, rows() * 18 + 17);
        this.blit(i, j + rows() * 18 + 17, 0, 126, this.xSize, 96);
    }

}