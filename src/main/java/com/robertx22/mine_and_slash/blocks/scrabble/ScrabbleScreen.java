package com.robertx22.mine_and_slash.blocks.scrabble;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.blocks.bases.BaseTileGui;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.ScrabblePacket;
import com.robertx22.mine_and_slash.uncommon.Res;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.glfw.GLFW;

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
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        super.init();

        String s = this.searchBar != null ? this.searchBar.getText() : "guess here";
        this.searchBar = new TextFieldWidget(
            this.mc.fontRenderer, this.guiLeft + xSize / 2 - 40, this.guiTop + ySize / 2, 80, 9 + 5,
            I18n.format("itemGroup.search")
        );
        this.searchBar.setMaxStringLength(20);
        this.searchBar.setEnableBackgroundDrawing(false);
        this.searchBar.setVisible(true);
        this.searchBar.setTextColor(TextFormatting.YELLOW.getColor());
        this.searchBar.setText(s);
        searchBar.setEnableBackgroundDrawing(true);

        this.addButton(searchBar);
    }

    @Override
    public boolean keyPressed(int key, int y, int idk) {

        super.keyPressed(key, y, idk);

        if (key == GLFW.GLFW_KEY_ENTER) {
            if (tile != null) {
                MMORPG.sendToServer(new ScrabblePacket(tile.getPos(), searchBar.getText()));

                Minecraft.getInstance()
                    .displayGuiScreen(null);
            }
        }

        return true;
    }

    private TextFieldWidget searchBar;

    @Override
    public void render(int x, int y, float t) {

        super.render(x, y, t);

        if (tile != null) {
            String str = tile.letters;
            mc.fontRenderer.drawStringWithShadow(str, mc.mainWindow.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(str) / 2, mc.mainWindow.getScaledHeight() / 2 - mc.fontRenderer.FONT_HEIGHT / 2 - ySize / 2 + 20, TextFormatting.GREEN.getColor());
        }
    }
}
