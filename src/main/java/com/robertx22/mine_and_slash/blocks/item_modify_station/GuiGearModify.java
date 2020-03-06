package com.robertx22.mine_and_slash.blocks.item_modify_station;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.blocks.bases.TileGui;
import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.currency.base.IAddsInstability;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.LocReqContext;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.IInstability;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GuiGearModify extends TileGui<ContainerGearModify, TileGearModify> {

    // This is the resource location for the background image
    private static final ResourceLocation texture = new ResourceLocation(Ref.MODID, "textures/gui/modify_station.png");

    public GuiGearModify(ContainerGearModify cont, PlayerInventory invPlayer, ITextComponent comp) {
        super(cont, invPlayer, comp, TileGearModify.class);

        xSize = 256;
        ySize = 207;

    }

    // some [x,y] coordinates of graphical elements
    final int COOK_BAR_XPOS = 49;
    final int COOK_BAR_YPOS = 60;
    final int COOK_BAR_ICON_U = 0; // texture position of white arrow icon
    final int COOK_BAR_ICON_V = 207;
    final int COOK_BAR_WIDTH = 80;
    final int COOK_BAR_HEIGHT = 17;

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {

        // Bind the image texture
        Minecraft.getInstance()
            .getTextureManager()
            .bindTexture(texture);
        // Draw the image
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        blit(guiLeft, guiTop, 0, 0, xSize, ySize);

        // draw the cook progress bar
        blit(
            guiLeft + 85, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V,
            (int) (this.tile.fractionOfCookTimeComplete() * COOK_BAR_WIDTH), COOK_BAR_HEIGHT
        );

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        LocReqContext context = tile.getLocReqContext();
        if (context.effect != null && context.hasStack()) {
            int y = 80;

            boolean add = true;

            for (BaseLocRequirement req : context.effect.requirements()) {
                if (req.isNotAllowed(context)) {
                    String txt = CLOC.translate(req.getText());

                    if (add) {
                        String reqtext = CLOC.translate(Words.RequirementsNotMet.locName()) + ": ";

                        font.drawString(
                            reqtext, this.xSize / 2 - font.getStringWidth(reqtext) / 2, y, Color.red.getRGB());
                        y += font.FONT_HEIGHT + 1;
                        add = false;
                    }

                    font.drawString(txt, this.xSize / 2 - font.getStringWidth(txt) / 2, y, Color.red.getRGB());
                    y += font.FONT_HEIGHT;
                }
            }

            if (ModConfig.INSTANCE.Server.ENABLE_CURRENCY_ITEMS_INSTABILITY_SYSTEM.get()) {
                if (context.Currency.getItem() instanceof IAddsInstability) {
                    IAddsInstability insta = (IAddsInstability) context.Currency.getItem();

                    if (context.data instanceof IInstability) {
                        IInstability i = (IInstability) context.data;

                        if (i.isInstabilityCapReached()) {
                            String breaktxt = Words.InstabilityLimitReached.translate();
                            font.drawString(
                                breaktxt, this.xSize / 2 - font.getStringWidth(breaktxt) / 2, y,
                                Color.red.getRGB()
                            );
                            y += font.FONT_HEIGHT;

                        } else {
                            if (ModConfig.INSTANCE.Server.ENABLE_CURRENCY_ITEMS_BREAKING_MODIFIED_ITEMS.get()) {
                                if (insta.activatesBreakRoll()) {

                                    float breakChance =
                                        (insta.additionalBreakChance() + i.getBreakChance()) * insta.breakChanceMulti();

                                    if (breakChance > 0) {
                                        String breaktxt = Words.BreakChance.translate() + ": " + String.format(
                                            "%.1f", breakChance) + "%";
                                        font.drawString(
                                            breaktxt, this.xSize / 2 - font.getStringWidth(breaktxt) / 2, y,
                                            Color.red.getRGB()
                                        );
                                        y += font.FONT_HEIGHT;

                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;
        font.drawString(CLOC.translate(tile.getDisplayName()), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());

        List<String> hoveringText = new ArrayList<String>();

        // If the mouse is over the progress bar add the progress bar hovering text
        if (GuiUtils.isInRect(
            guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX, mouseY)) {
            hoveringText.add(Words.Progress.translate() + ": ");
            int cookPercentage = (int) (this.tile.fractionOfCookTimeComplete() * 100);
            hoveringText.add(cookPercentage + "%");
        }

        // If hoveringText is not empty draw the hovering text
        if (!hoveringText.isEmpty()) {

            renderTooltip(hoveringText, mouseX - guiLeft, mouseY - guiTop, font);
        }
    }

}