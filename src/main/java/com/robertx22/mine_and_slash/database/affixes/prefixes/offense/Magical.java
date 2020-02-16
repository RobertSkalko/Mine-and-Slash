package com.robertx22.mine_and_slash.database.affixes.prefixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.SpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Magical extends Prefix {

    public Magical() {
        super(new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL10()));
    }

    @Override
    public String GUID() {
        return "magical";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new SpellDamagePercent(), new CoreStatFlat(Intelligence.INSTANCE));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public String locNameForLangFile() {
        return "Magical";
    }
}
