package com.robertx22.mine_and_slash.database.affixes.prefixes.offense;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.offense.SpellDamagePercent;

import java.util.Arrays;
import java.util.List;

public class TouchOfMagic extends Prefix {

    @Override
    public String GUID() {
        return "touch_of_magic";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new SpellDamagePercent());

    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly());
    }

    @Override
    public String locNameForLangFile() {
        return "Touch of Magic";
    }
}
