package com.robertx22.stats.StatEffects.spell_buffs;

import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.stats.StatEffects.spell_buffs.base.BaseSpellBuff;

public class GhostProjectileBuff extends BaseSpellBuff {

    @Override
    public int GetPriority() {
	return 11;
    }

    @Override
    public SpellType typeOfSpellAffected() {
	return SpellType.Single_Target_Projectile;
    }

    @Override
    public SpellBuffType buffType() {
	return SpellBuffType.Ghost_Projectile;
    }

}
