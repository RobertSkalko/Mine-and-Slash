package com.robertx22.mine_and_slash.database.affixes.suffixes.curses;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfLessCoreStat extends Suffix {

    public static OfLessCoreStat DEX = new OfLessCoreStat("of_clumsiness", "Of Clumsiness", Dexterity.INSTANCE);
    public static OfLessCoreStat STA = new OfLessCoreStat("of_exhaustion", "Of Exhaustion", Stamina.INSTANCE);
    public static OfLessCoreStat VIT = new OfLessCoreStat("of_frailty", "Of Frailty", Vitality.INSTANCE);
    public static OfLessCoreStat INT = new OfLessCoreStat("of_idiocy", "Of Idiocy", Intelligence.INSTANCE);
    public static OfLessCoreStat WIS = new OfLessCoreStat("of_inexperience", "Of Inexperience", Wisdom.INSTANCE);
    public static OfLessCoreStat STR = new OfLessCoreStat("of_feebleness", "Of Feebleeness", Strength.INSTANCE);

    public OfLessCoreStat() {
        super(new Requirements(SlotRequirement.allExceptWeapon(), LevelRequirement.fromLowLevel()));
    }

    String id;
    String locName;
    BaseCoreStat stat;

    public OfLessCoreStat(String id, String locName, BaseCoreStat stat) {
        super(new Requirements(SlotRequirement.allExceptWeapon(), LevelRequirement.fromLowLevel()));
        this.id = id;
        this.locName = locName;
        this.stat = stat;
    }

    @Override
    public String GUID() {
        return id;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CoreStatFlat(stat).size(StatMod.Size.LESS));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return locName;
    }

}

