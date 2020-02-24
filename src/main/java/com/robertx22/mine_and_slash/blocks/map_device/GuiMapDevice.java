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

        this.addButton(new MapGetGroupMapButton(tile.getPos(), guiLeft + 10, guiTop + 95));

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

            if (tile == null || tile.mapDeviceData == null) {
                return;
            }

            final int REM_XPOS = 5;
            final int REM_YPOS = 15;
            //  font.drawString("Remaining Group Map Tickets: " + tile.mapDeviceData.getRemainingTickets(), REM_XPOS, REM_YPOS, Color.darkGray
            //         .getRGB());

            String text = "Take Group Map Ticket";

            final int textx = 10 + (MapGetGroupMapButton.xSize / 2) - font.getStringWidth(text) / 2;
            final int texty = 100;

            font.drawString(text, textx, texty, Color.white.getRGB());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}