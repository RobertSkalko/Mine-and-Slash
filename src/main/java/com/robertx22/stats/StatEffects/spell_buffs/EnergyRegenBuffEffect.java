package com.robertx22.stats.StatEffects.spell_buffs;

import com.robertx22.effectdatas.interfaces.IBuffableSpell.SpellBuffType;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.stats.StatEffects.spell_buffs.base.BaseSpellBuff;

public class EnergyRegenBuffEffect extends BaseSpellBuff {

    @Override
    public SpellType typeOfSpellAffected() {
	return SpellType.Aoe_Bomb_Projectile;
    }

    @Override
    public SpellBuffType buffType() {
	return SpellBuffType.Energy_Regen;
    }

}
