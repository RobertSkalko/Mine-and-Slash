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
        ONE(1),
        FIVE(5),
        TEN(10),
        TWENTY_FIVE(25),
        FOURTY(40),
        FIFTY(50),
        SIXTY(60),
        SEVENTY_FIVE(75),
        NINETY(90),
        HUNDRED(100);

        Levels(int num) {
            this.number = num;
        }

        public int number;

    }
}
