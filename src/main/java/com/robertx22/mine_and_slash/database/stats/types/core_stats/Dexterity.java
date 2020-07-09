package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Dexterity extends BaseCoreStat {

    public static final String GUID = "dexterity";

    public static final Dexterity INSTANCE = new Dexterity();

    private Dexterity() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.GREEN;
    }

    @Override
    public String getIconPath() {
        return "core/dex";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Crit Hit, Dodge and Armor";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatModifier> statsThatBenefit() {
        return Arrays.asList(
            new StatModifier(2F, DodgeRating.getInstance(), StatModTypes.GLOBAL_INCREASE),
            new StatModifier(0.5F, CriticalHit.getInstance(), StatModTypes.Flat)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Dexterity";
    }
}
