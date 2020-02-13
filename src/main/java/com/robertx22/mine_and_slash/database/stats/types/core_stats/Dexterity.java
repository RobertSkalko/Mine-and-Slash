package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Dexterity extends BaseCoreStat {

    public static final String GUID = "Dexterity";

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
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(
                new CriticalHitFlat().multi(1.5F), new DodgeRatingFlat().multi(1.5F), new ArmorFlat().multi(0.5F));
    }

    @Override
    public String locNameForLangFile() {
        return "Dexterity";
    }
}
