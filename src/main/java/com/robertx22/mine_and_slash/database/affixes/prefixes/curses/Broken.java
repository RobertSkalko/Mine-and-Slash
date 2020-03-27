package com.robertx22.mine_and_slash.database.affixes.prefixes.curses;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Broken extends Prefix {

    public Broken() {
        super(new Requirements(SlotRequirement.weaponsOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "broken";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new PhysicalDamageFlat().size(StatMod.Size.LESS));
    }

    @Override
    public String locNameForLangFile() {
        return "Broken";
    }
}

