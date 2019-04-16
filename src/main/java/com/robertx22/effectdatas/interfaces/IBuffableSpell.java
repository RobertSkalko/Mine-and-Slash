package com.robertx22.effectdatas.interfaces;

public interface IBuffableSpell {

    enum SpellBuffType {
	None, Homing_Projectile, Aoe_On_Proj_Kill_Target
    }

    void setBuff(SpellBuffType buff);

    SpellBuffType getBuff();
}
