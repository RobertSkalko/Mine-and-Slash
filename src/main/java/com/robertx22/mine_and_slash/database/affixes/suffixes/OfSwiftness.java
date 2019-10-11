package com.robertx22.mine_and_slash.database.affixes.suffixes;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.DexterityFlat;

import java.util.Arrays;
import java.util.List;

public class OfSwiftness extends Suffix {

    public OfSwiftness() {
        super(new Requirements(SlotRequirement.armorsOnly()));
    }

    @Override
    public String GUID() {
        return "of_swiftness";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new DexterityFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Of Swiftness";
    }
}
