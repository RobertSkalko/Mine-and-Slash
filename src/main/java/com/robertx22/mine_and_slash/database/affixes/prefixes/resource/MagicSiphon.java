package com.robertx22.mine_and_slash.database.affixes.prefixes.resource;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicStealFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class MagicSiphon extends Prefix {

    public MagicSiphon() {
        super(new Requirements(SlotRequirement.weaponsOnly(), LevelRequirement.fromMidLevel()));
    }

    @Override
    public String GUID() {
        return "magic_siphon";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new MagicStealFlat().size(StatMod.Size.HIGH));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Magic Siphon";
    }
}