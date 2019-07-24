package com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs;

import com.robertx22.mine_and_slash.database.stats.stat_effects.spell_buffs.base.BaseSpellBuff;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell.SpellType;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IBuffableSpell.SpellBuffType;

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
