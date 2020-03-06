package com.robertx22.mine_and_slash.database.currency.base;

import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;

public interface IShapedRecipe {
    ShapedRecipeBuilder getRecipe();

    default ShapedRecipeBuilder shaped(IItemProvider pro) {
        return ShapedRecipeBuilder.shapedRecipe(pro, 1);
    }
}



