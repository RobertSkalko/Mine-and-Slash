package com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits;

import com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs.ZephyrBuff;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class ZephyrTrait extends SpellBuffTrait {

    public static String GUID = "Zephyr's Speed";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public IStatEffect getEffect() {
        return new ZephyrBuff();
    }

    @Override
    public String locDescForLangFile() {
        return "Your Self Heal Spells now boost your speed temporarily.";
    }

    @Override
    public String locNameForLangFile() {
        return "Zephyr";
    }
}
