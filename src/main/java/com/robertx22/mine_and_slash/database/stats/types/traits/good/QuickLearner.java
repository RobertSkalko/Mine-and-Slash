package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class QuickLearner extends Trait implements IAffectsOtherStats {

    public static String GUID = "quick_learner";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new BonusExpFlat());

    }

    @Override
    public String locNameForLangFile() {
        return "Quick Learner";
    }
}

