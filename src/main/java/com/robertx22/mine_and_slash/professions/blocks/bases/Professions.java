package com.robertx22.mine_and_slash.professions.blocks.bases;

import com.robertx22.mine_and_slash.mmorpg.registers.common.ModTileEntities;
import com.robertx22.mine_and_slash.professions.recipe.BaseMaterial;
import com.robertx22.mine_and_slash.professions.recipe.BaseOutputItem;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public enum Professions {

    ALCHEMY(Words.Alchemy, ModTileEntities.ALCHEMY.get());

    Professions(Words word, TileEntityType<?> tileEntityType) {
        this.tileEntityType = tileEntityType;
        this.word = word;

    }

    public static int MAX_LEVEL = 1000;
    public static int MAX_LEVEL_OF_RECIPES = 100;

    public List<BaseRecipe> recipes() {
        return SlashRegistry.Recipes()
            .getFiltered(x -> x.profession()
                .equals(this));
    }

    public Words word;
    public TileEntityType<?> tileEntityType;

    public float getReduceMatConsumptionChance(int lvl) {
        return (float) 20 / MAX_LEVEL * lvl;
    }

    public float getBonusOutputChance(int lvl) {
        return (float) 20 / MAX_LEVEL * lvl;
    }

    public int getOutputAmount(ProfessionTile tile, BaseOutputItem output, int lvl) {
        ItemStack stack = output.generateStack(tile);

        if (stack.getMaxStackSize() > 1) {
            float chance = this.getBonusOutputChance(lvl);
            if (RandomUtils.roll(chance)) {
                return stack.getCount() * 2;
            }
        }
        return stack.getCount();
    }

    public int tryReduceMaterialRequirement(ProfessionTile tile, BaseMaterial mat,
                                            int lvl) {

        if (mat.getItemStack()
            .getCount() > 1) {
            float chance = this.getReduceMatConsumptionChance(lvl);
            if (RandomUtils.roll(chance)) {
                return mat.getItemStack()
                    .getCount() / 2;
            }
        }

        return mat.getItemStack()
            .getCount();
    }

    public enum Levels {
        ONE(1, 1, "Minor", TextFormatting.GRAY, 0.75F),
        TEN(10, 1.1F, "Lesser", TextFormatting.GREEN, 1F),
        TWENTY_FIVE(25, 1.2F, "Medium", TextFormatting.YELLOW, 1.5F),
        FIFTY(50, 1.5F, "Greater", TextFormatting.BLUE, 2F),
        SEVENTY_FIVE(75, 2F, "Major", TextFormatting.GOLD, 3F),
        HUNDRED(100, 3F, "Miraculous", TextFormatting.LIGHT_PURPLE, 5F);

        Levels(int num, float cost, String name, TextFormatting color,
               float effectMultiplier) {
            this.number = num;
            this.materialCostMulti = cost;
            this.name = name;
            this.color = color;
            this.effectMultiplier = effectMultiplier;

        }

        public TextFormatting color;
        public String name;
        public int number;
        public float materialCostMulti = 1;

        public float effectMultiplier = 1;

    }
}
