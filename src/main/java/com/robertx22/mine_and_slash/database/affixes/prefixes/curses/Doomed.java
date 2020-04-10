package com.robertx22.mine_and_slash.database.affixes.prefixes.curses;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.FasterCastRateFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Doomed extends Prefix {

    public Doomed() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "doomed";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new FasterCastRateFlat().size(StatMod.Size.DOUBLE_LESS)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Doomed";
    }
}
