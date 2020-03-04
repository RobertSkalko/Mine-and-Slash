package com.robertx22.mine_and_slash.blocks.scrabble;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.blocks.bases.BaseTileGui;
import com.robertx22.mine_and_slash.uncommon.Res;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;

public class ScrabbleScreen extends BaseTileGui<ScrabbleTile> {

    private static final ResourceLocation TEX = Res.loc("textures/gui/scrabble.png");

    public ScrabbleScreen(BlockPos pos) {
        super(ScrabbleTile.class, pos);

    }

    @Override
    public void renderBackground() {

        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(TEX);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

    @Override
    public void render(int x, int y, float t) {

        super.render(x, y, t);

        String str = tile.letters;
        mc.fontRenderer.drawStringWithShadow(str, mc.mainWindow.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(str) / 2, mc.mainWindow.getScaledHeight() / 2 - mc.fontRenderer.FONT_HEIGHT / 2 - ySize / 2 + 20, TextFormatting.GREEN.getColor());

    }
}
