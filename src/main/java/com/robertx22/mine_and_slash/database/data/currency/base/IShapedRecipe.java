package com.robertx22.mine_and_slash.database.data.currency.base;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.EnchantedItemTrigger;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;

public interface IShapedRecipe {
    ShapedRecipeBuilder getRecipe();

    default ShapedRecipeBuilder shaped(IItemProvider pro) {
        return ShapedRecipeBuilder.shapedRecipe(pro, 1);
    }

    default ShapedRecipeBuilder shaped(IItemProvider pro, int i) {
        return ShapedRecipeBuilder.shapedRecipe(pro, i);

    }

    default ICriterionInstance trigger() {
        return EnchantedItemTrigger.Instance.any();
    }
}



