package com.robertx22.mine_and_slash.database.affixes.prefixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicStealFlat;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class UndeadCurse extends Prefix {

    public UndeadCurse() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "cursed_undead";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new HealthFlat().size(StatMod.Size.LESS), new MagicStealFlat());
    }

    @Override
    public String locNameForLangFile() {
        return "Cursed Undead";
    }
}


