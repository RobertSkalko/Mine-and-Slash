package com.robertx22.database.stat_types.spell_buff_traits;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.stats.IStatEffect;
import com.robertx22.stats.StatEffects.spell_buffs.LightBuff;

public class LightTrait extends SpellBuffTrait {

    public static String GUID = "Light";

    @Override
    public String Guid() {
	return GUID;
    }

    @Override
    public String Description() {
	return "Your Self Heal Spells now grant you Aoe Regen";

    }

    @Override
    public String unlocString() {
	return "light";
    }

    @Override
    public List<IStatEffect> GetEffects() {
	return Arrays.asList(new LightBuff());
    }

}
