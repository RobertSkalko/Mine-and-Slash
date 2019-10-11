package com.robertx22.mine_and_slash.database.affixes.prefixes.defense;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class Hardened extends Prefix {

    public Hardened() {
        super(new Requirements(SlotRequirement.armorsOnly()));
    }

    @Override
    public String GUID() {
        return "Hardened";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new ArmorPercent());

    }

    @Override
    public String locNameForLangFile() {
        return "Hardened";
    }
}
