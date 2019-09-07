package com.robertx22.mine_and_slash.new_content_test.professions.data;

import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;
import java.util.List;

public enum Professions {
    ALCHEMY(AlchemyRecipes.ALL, BlockRegister.ALCHEMY_TILE);

    Professions(List<BaseRecipe> recipes, TileEntityType<?> tileEntityType) {
        this.recipes = recipes;
        this.tileEntityType = tileEntityType;
    }

    public List<BaseRecipe> recipes = new ArrayList<>();
    public TileEntityType<?> tileEntityType;
}
