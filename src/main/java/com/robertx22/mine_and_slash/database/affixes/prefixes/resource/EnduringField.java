package com.robertx22.mine_and_slash.database.affixes.prefixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class EnduringField extends Prefix {

    public EnduringField() {
        super(new Requirements(SlotRequirement.shield()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "enduring_field";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MagicShieldFlat().multi(2));
    }

    @Override
    public String locNameForLangFile() {
        return "Enduring Field";
    }
}
