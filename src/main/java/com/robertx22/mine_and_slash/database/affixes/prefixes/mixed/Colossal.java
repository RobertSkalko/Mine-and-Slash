package com.robertx22.mine_and_slash.database.affixes.prefixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Colossal extends Prefix {

    public Colossal() {
        super(new Requirements(SlotRequirement.weaponsOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String GUID() {
        return "colossal";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new EnergyRegenFlat().size(StatMod.Size.ONE_LESS),
            new PhysicalDamagePercent().size(StatMod.Size.DOUBLE));
    }

    @Override
    public String locNameForLangFile() {
        return "Colossal";
    }
}

