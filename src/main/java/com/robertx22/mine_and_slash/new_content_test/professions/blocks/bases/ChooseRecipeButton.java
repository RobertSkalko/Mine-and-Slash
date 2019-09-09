package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.OpenProfessionCraftingPacket;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.ItemStackHandler;

public class ChooseRecipeButton extends ImageButton {

    static int xSize = 75;
    static int ySize = 29;
    static ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/choose_recipe_button.png");
    int playerProfLvl;
    RecipeSlot slot;
    BlockPos pos;
    BaseRecipe recipe;
    boolean meetsReq;

    static int expImgY = 60;
    static int NOImgY = 70;
    static int YESImgY = 78;

    static int YESNOSize = 7;
    static int expSize = 9;

    public ChooseRecipeButton(int playerProfLvl, BaseRecipe recipe, ItemStack output,
                              int xPos, int yPos, BlockPos pos) {
        super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
        });

        ItemStackHandler handler = new ItemStackHandler(1);
        handler.setStackInSlot(0, output);
        slot = new RecipeSlot(handler, 0, x + xSize - 16 - 16 / 2, y + ySize / 2 - 16 / 2);
        this.pos = pos;
        this.recipe = recipe;
        this.playerProfLvl = playerProfLvl;
        this.meetsReq = playerProfLvl >= recipe.professionLevelReq;
    }

    @Override
    public void onClick(double x, double y) {
        super.onClick(x, y);
        if (isInside((int) x, (int) y)) {
            MMORPG.sendToServer(new RequestTilePacket(pos));
            MMORPG.sendToServer(new OpenProfessionCraftingPacket(pos, recipe));
        }
    }

    public boolean isInside(int x, int y) {
        return isInRect(this.x, this.y, xSize, ySize, x, y);
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX,
                                   int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    @Override
    public void renderButton(int x, int y, float f) {

        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(img);
        GlStateManager.disableDepthTest();

        int offsetY = 0;
        if (this.isHovered()) {
            offsetY += ySize + 1;
        }

        int offsetX = 0;
        if (this.recipe.professionLevelReq > playerProfLvl) {
            offsetX = xSize + 1;
        }

        blit(this.x, this.y, (float) offsetX, (float) offsetY, this.width, this.height, 256, 256);

        int xPos = this.x + xSize / 2 - 30;
        int yPos = this.y + ySize / 2 - expSize / 2;

        if (this.meetsReq) {
            blit(xPos, yPos + 1, 0, YESImgY, YESNOSize, YESNOSize, 256, 256);
        } else {
            blit(xPos, yPos + 1, 0, NOImgY, YESNOSize, YESNOSize, 256, 256);
        }

        blit(xPos + YESNOSize + 3, yPos, 0, expImgY, expSize, expSize, 256, 256);

        int xTextPos = xPos + YESNOSize + expSize + 6;

        mc.fontRenderer.drawString(this.recipe.professionLevelReq + "", xTextPos, yPos + 1, TextFormatting.YELLOW
                .getColor());

        GlStateManager.enableDepthTest();
    }

}
