package com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces;

import com.robertx22.mine_and_slash.spells.bases.BaseSpell;

public interface IBuffableSpell {

    enum SpellBuffType {
	None, Homing_Projectile, Ghost_Projectile, Triple_Projectile_Cone, Aoe_On_Proj_Kill_Target, Zephyr_Speed_Boost,
	Light_Aoe_Regen, Purity_Remove_Negative_Effects, Energy_Regen, Mana_Regen
    }

    void setBuff(SpellBuffType buff);

    SpellBuffType getBuff();

    void setBuffType(BaseSpell.SpellType type);

    BaseSpell.SpellType getBuffType();
}
