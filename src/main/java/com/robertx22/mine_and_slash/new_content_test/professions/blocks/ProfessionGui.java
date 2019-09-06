package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class ProfessionGui<T extends ProfessionContainer, Tile extends ProfessionRecipesTile> extends ContainerScreen<T> {

    ProfessionScreenType screenType = ProfessionScreenType.RECIPES;
    public Tile tile;
    Minecraft mc;

    public ProfessionGui(T cont, PlayerInventory inv, ITextComponent text,
                         Class<Tile> token) {
        super(cont, inv, text);

        this.mc = Minecraft.getInstance();

        if (cont.pos != null) {
            TileEntity en = Minecraft.getInstance().world.getTileEntity(cont.pos);
            if (en != null) {
                if (token.isAssignableFrom(en.getClass())) {
                    this.tile = (Tile) en;
                }
            }
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {

        if (tile != null) {
            if (mc.player.ticksExisted % 20 == 0) {
                MMORPG.sendToServer(new RequestTilePacket(tile.getPos()));
            }
        }

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        Minecraft.getInstance().getTextureManager().bindTexture(getCurrentGuiTexture());
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawExtraGuiStuff(partialTicks, x, y);

    }

    public abstract void drawExtraGuiStuff(float partialTicks, int x, int y);

    public ResourceLocation getCurrentGuiTexture() {

        if (screenType == ProfessionScreenType.CRAFTING) {
            return this.getCraftingTexture();
        }
        if (screenType == ProfessionScreenType.MODIFYING) {
            return this.getModifyingTexture();
        }
        if (screenType == ProfessionScreenType.RECIPES) {
            return this.getRecipeTexture();
        }

        return null;
    }

    public abstract ResourceLocation getRecipeTexture();

    public abstract ResourceLocation getCraftingTexture();

    public abstract ResourceLocation getModifyingTexture();

}
