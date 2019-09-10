package com.robertx22.mine_and_slash.new_content_test.professions.data;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.tileentity.TileEntityType;

import java.util.List;

public enum Professions {

    ALCHEMY(Words.Alchemy, BlockRegister.ALCHEMY_TILE);

    Professions(Words word, TileEntityType<?> tileEntityType) {
        this.tileEntityType = tileEntityType;
        this.word = word;

    }

    public List<BaseRecipe> recipes() {
        return SlashRegistry.Recipes().getFiltered(x -> x.profession().equals(this));
    }

    public Words word;
    public TileEntityType<?> tileEntityType;

    public enum Levels {
        ONE(1, 1),
        FIVE(5, 1.05F),
        TEN(10, 1.1F),
        TWENTY_FIVE(25, 1.2F),
        FOURTY(40, 1.3F),
        FIFTY(50, 1.5F),
        SIXTY(60, 1.7F),
        SEVENTY_FIVE(75, 2F),
        NINETY(90, 2.5F),
        HUNDRED(100, 3F);

        Levels(int num, float cost) {
            this.number = num;
            this.materialCostMulti = cost;

        }

        public int number;
        public float materialCostMulti = 1;
    }
}
