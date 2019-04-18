package com.robertx22.database.stat_types.spell_buff_traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.StatEffects.spell_buffs.HomingBuff;

public class HomingTrait extends SpellBuffTrait {

    public static String GUID = "Homing";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public String unlocString() {
	return "homing_projectile";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new HomingBuff());
    }

}
