package com.robertx22.mine_and_slash.database.affixes.prefixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class UnendingField extends Prefix {

    public UnendingField() {
        super(new Requirements(SlotRequirement.ring()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "unending_field";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MagicShieldRegenFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Unending Field";
    }
}
