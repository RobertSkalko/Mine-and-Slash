package com.robertx22.stats.StatEffects.spell_buffs;

import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.stats.StatEffects.spell_buffs.base.BaseSpellBuff;

public class PurityBuff extends BaseSpellBuff {

    @Override
    public int GetPriority() {
	return 10;
    }

    @Override
    public SpellType typeOfSpellAffected() {
	return SpellType.Self_Heal;
    }

    @Override
    public SpellBuffType buffType() {
	return SpellBuffType.Purity_Remove_Negative_Effects;
    }

}
