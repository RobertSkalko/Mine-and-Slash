package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.blocks.bases.BaseTileContainer;
import com.robertx22.mine_and_slash.blocks.bases.TileGui;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class ProfessionGui extends TileGui {

    public enum ScreenType {
        RECIPES,
        CRAFTING,
        MODIFYING
    }

    ScreenType screenType = ScreenType.RECIPES;

    public ProfessionGui(BaseTileContainer cont, PlayerInventory inv, ITextComponent text,
                         Class token) {
        super(cont, inv, text, token);
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

        if (screenType == ScreenType.CRAFTING) {
            return this.getCraftingTexture();
        }
        if (screenType == ScreenType.MODIFYING) {
            return this.getModifyingTexture();
        }
        if (screenType == ScreenType.RECIPES) {
            return this.getRecipeTexture();
        }

        return null;
    }
    
    public abstract ResourceLocation getRecipeTexture();

    public abstract ResourceLocation getCraftingTexture();

    public abstract ResourceLocation getModifyingTexture();

}
