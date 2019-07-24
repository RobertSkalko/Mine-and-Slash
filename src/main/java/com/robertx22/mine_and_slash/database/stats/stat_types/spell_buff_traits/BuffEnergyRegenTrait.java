package com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits;

import com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs.EnergyRegenBuffEffect;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

import java.util.Arrays;
import java.util.List;

public class BuffEnergyRegenTrait extends SpellBuffTrait {

    public static String GUID = "Buff Energy Regen";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<IStatEffect> GetEffects() {
        return Arrays.asList(new EnergyRegenBuffEffect());
    }

    @Override
    public String locDescForLangFile() {
        return "Killing Blows with Bomb Spells grant you Energy Regen.";
    }

    @Override
    public String locNameForLangFile() {
        return "Buff Energy Regen";
    }
}
