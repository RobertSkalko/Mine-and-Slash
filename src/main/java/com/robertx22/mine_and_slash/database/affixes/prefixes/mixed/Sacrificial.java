package com.robertx22.mine_and_slash.database.affixes.prefixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.Mod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Sacrificial extends Prefix {

    public Sacrificial() {
        super(new Requirements(SlotRequirement.charm()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "sacrificial";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new HealthFlat().size(StatMod.Size.HALF_LESS),
            Mod.LUCK_FLAT()
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Sacrificial";
    }
}
