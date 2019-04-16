package com.robertx22.effectdatas.interfaces;

import com.robertx22.spells.bases.BaseSpell.SpellType;

public interface IBuffableSpell {

    enum SpellBuffType {
	None, Homing_Projectile, Aoe_On_Proj_Kill_Target
    }

    void setBuff(SpellBuffType buff);

    SpellBuffType getBuff();

    void setType(SpellType type);

    SpellType getType();
}
