package com.robertx22.database.stats.stat_effects.spell_buffs;

import com.robertx22.database.stats.stat_effects.spell_buffs.base.BaseSpellBuff;
import com.robertx22.spells.bases.BaseSpell.SpellType;
import com.robertx22.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;

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
