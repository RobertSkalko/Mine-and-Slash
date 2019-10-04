package com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits;

import com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs.ManaRegenBuffEffect;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class BuffManaRegenTrait extends SpellBuffTrait {

    public static String GUID = "Buff Mana Regen";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public IStatEffect getEffect() {
        return new ManaRegenBuffEffect();

    }

    @Override
    public String locDescForLangFile() {
        return "Killing Blows with Bomb Spells grant you Mana Regen.";
    }

    @Override
    public String locNameForLangFile() {
        return "Buff Mana Regen";
    }
}
