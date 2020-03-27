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

public class GenerationalCurse extends Prefix {

    public GenerationalCurse() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "generation_curse";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new HealthFlat().size(StatMod.Size.LESS),
            Mod.LUCK_FLAT()
                .size(StatMod.Size.LESS)
                .size(StatMod.Size.HIGH)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "Generational Curse";
    }
}
