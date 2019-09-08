package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.blocks.slots.handlerslots.RecipeSlot;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.network.OpenProfessionCraftingPacket;
import com.robertx22.mine_and_slash.network.RequestTilePacket;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

public class ChooseRecipeButton extends ImageButton {

    static int xSize = 80;
    static int ySize = 29;
    static ResourceLocation img = new ResourceLocation(Ref.MODID, "textures/gui/choose_recipe_button.png");

    RecipeSlot slot;
    BlockPos pos;

    BaseRecipe recipe;

    public ChooseRecipeButton(BaseRecipe recipe, ItemStack output, int xPos, int yPos,
                              BlockPos pos) {
        super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, img, (button) -> {
        });

        ItemStackHandler handler = new ItemStackHandler(1);
        handler.setStackInSlot(0, output);
        slot = new RecipeSlot(handler, 0, x, y);
        this.pos = pos;
        this.recipe = recipe;
    }

    @Override
    public void onClick(double x, double y) {
        super.onClick(x, y);

        MMORPG.sendToServer(new RequestTilePacket(pos));
        MMORPG.sendToServer(new OpenProfessionCraftingPacket(pos, recipe));
    }

    @Override
    public void renderButton(int x, int y, float f) {
        super.renderButton(x, y, f);

        if (this.isHovered()) {

        }

    }

}
