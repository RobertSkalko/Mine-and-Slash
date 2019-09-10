package com.robertx22.mine_and_slash.new_content_test.professions.data;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;

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
        ONE(1, 1, "Minor", TextFormatting.GRAY),
        TEN(10, 1.1F, "Lesser", TextFormatting.GREEN),
        TWENTY_FIVE(25, 1.2F, "Medium", TextFormatting.YELLOW),
        FIFTY(50, 1.5F, "Greater", TextFormatting.BLUE),
        SEVENTY_FIVE(75, 2F, "Major", TextFormatting.GOLD),
        HUNDRED(100, 3F, "Miraculous", TextFormatting.LIGHT_PURPLE);

        Levels(int num, float cost, String name, TextFormatting color) {
            this.number = num;
            this.materialCostMulti = cost;
            this.name = name;
            this.color = color;

        }

        public TextFormatting color;
        public String name;
        public int number;
        public float materialCostMulti = 1;
    }
}
