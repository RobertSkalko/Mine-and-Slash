package com.robertx22.mine_and_slash.professions.recipe;

import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RecipeItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Recipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SimpleOutputItem extends BaseOutputItem {

    ItemStack output;
    BaseRecipe recipe;

    public SimpleOutputItem(Item item, BaseRecipe recipe) {
        output = new ItemStack(item);
        this.recipe = recipe;
    }

    public SimpleOutputItem setAmount(int amount) {
        this.output.setCount(amount);
        return this;
    }

    @Override
    public ItemStack getPreview() {
        ItemStack copy = output.copy();
        Recipe.Save(copy, new RecipeItemData(recipe));
        return copy;
    }

    @Override
    public ItemStack generateStack(ProfessionTile tile) {
        return output.copy();
    }
}
