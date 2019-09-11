package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.widgets;

import com.mojang.blaze3d.platform.GlStateManager;
import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.ProfessionRecipePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseMaterial;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class ChooseRecipeButton extends ImageButton {

    public static int xSize = 135;
    public static int ySize = 20;
    static ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/profession/choose_recipe_button.png");
    public int playerProfLvl;
    public RecipeSlot slot;
    public BlockPos pos;
    public BaseRecipe recipe;
    public boolean meetsReq;

    public List<RecipeSlot> materialSlots = new ArrayList<>();

    static int expImgY = 60;
    static int NOImgY = 50;
    static int YESImgY = 40;

    static int YESNOSize = 10;
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

        int xOffsetItem = 0;

        for (BaseMaterial mat : recipe.getMaterials()) {
            handler = new ItemStackHandler(1);
            handler.setStackInSlot(0, mat.getPreview());
            this.materialSlots.add(new RecipeSlot(handler, 0, x + 5 + xOffsetItem, y + ySize / 2 - 16 / 2));
            xOffsetItem += 17;
        }

    }

    @Override
    public void onClick(double x, double y) {
        super.onClick(x, y);
        if (isInside((int) x, (int) y)) {
            MMORPG.sendToServer(new ProfessionRecipePacket(pos, recipe));

        }
    }

    public boolean isInsideSlot(Slot slot, int x, int y) {
        return this.isInRect(slot.xPos, slot.yPos, 16, 16, x, y);

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
            offsetY += ySize;
        }
        int offsetX = 0;

        blit(this.x, this.y, (float) offsetX, (float) offsetY, this.width, this.height, 256, 256);

        int xArrowPos = this.x + xSize - 35;
        int yArrowPos = this.y + ySize / 2 - YESNOSize / 2 + 1;

        if (this.meetsReq) {
            blit(xArrowPos, yArrowPos, 0, YESImgY, YESNOSize, YESNOSize, 256, 256);
        } else {
            blit(xArrowPos, yArrowPos, 0, NOImgY, YESNOSize, YESNOSize, 256, 256);
        }
        GlStateManager.enableDepthTest();
    }

}
