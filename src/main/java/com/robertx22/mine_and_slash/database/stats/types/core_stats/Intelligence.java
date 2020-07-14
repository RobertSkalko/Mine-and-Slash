package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Intelligence extends BaseCoreStat {

    private Intelligence() {

    }

    public static final Intelligence INSTANCE = new Intelligence();
    public static String GUID = "intelligence";

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.BLUE;
    }

    @Override
    public String getIconPath() {
        return "core/int";
    }

    @Override
    public String locDescForLangFile() {
        return "Increase Magic Shield percent, Mana Regen and Mana and All Elemental damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatModifier> statsThatBenefit() {
        return Arrays.asList(
            new StatModifier(1F, 1F, Mana.getInstance(), ModType.LOCAL_INCREASE),
            new StatModifier(1F, 1F, MagicShield.getInstance(), ModType.GLOBAL_INCREASE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Intelligence";
    }
}




