package com.robertx22.mine_and_slash.new_content_test.professions.data;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.tileentity.TileEntityType;

import java.util.List;

public enum Professions {
    ALCHEMY(BlockRegister.ALCHEMY_TILE);

    Professions(TileEntityType<?> tileEntityType) {
        this.tileEntityType = tileEntityType;

    }

    public List<BaseRecipe> recipes() {
        return SlashRegistry.Recipes().getFiltered(x -> x.profession().equals(this));
    }

    public TileEntityType<?> tileEntityType;
}
