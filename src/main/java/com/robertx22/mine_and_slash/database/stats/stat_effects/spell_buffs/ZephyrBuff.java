package com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs;

import com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs.base.BaseSpellBuff;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell.SpellType;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;

public class ZephyrBuff extends BaseSpellBuff {

    @Override
    public int GetPriority() {
        return 8;
    }

    @Override
    public SpellType typeOfSpellAffected() {
        return SpellType.Self_Heal;
    }

    @Override
    public SpellBuffType buffType() {
        return SpellBuffType.Zephyr_Speed_Boost;
    }

}
