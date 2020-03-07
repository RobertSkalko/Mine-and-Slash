package com.robertx22.mine_and_slash.database.affixes.suffixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicStealFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfMagicStealing extends Suffix {

    public OfMagicStealing() {
        super(new Requirements(SlotRequirement.weaponsOnly()));
    }

    @Override
    public String GUID() {
        return "of_magic_stealing";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MagicStealFlat());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Magic Stealing";
    }
}