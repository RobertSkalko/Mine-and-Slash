package com.robertx22.database.stat_types.spell_buff_traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.StatEffects.spell_buffs.ZephyrBuff;

public class ZephyrTrait extends SpellBuffTrait {

    public static String GUID = "Zephyr's Speed";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public String unlocString() {
	return "zephyr_speed";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new ZephyrBuff());
    }

}
