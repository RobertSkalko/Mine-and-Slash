package com.robertx22.mine_and_slash.blocks.map_device;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.blocks.bases.TileGui;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class GuiMapDevice extends TileGui<ContainerMapDevice, TileMapDevice> {

    // This is the resource location for the background image
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/map_device.png");

    public GuiMapDevice(ContainerMapDevice cont, PlayerInventory invPlayer,
                        ITextComponent comp) {

        super(cont, invPlayer, comp, TileMapDevice.class);

        xSize = 176;
        ySize = 207;

    }

    @Override
    protected void init() {
        super.init();
        super.init();

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        // Bind the image texture
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(texture);
        // Draw the image
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;
        font.drawString(Words.Map_Device.translate(), LABEL_XPOS, LABEL_YPOS, Color.darkGray
            .getRGB());

        try {

            if (tile == null) {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}