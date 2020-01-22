package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

public abstract class BaseProjectileSpell extends BaseBolt {

    @Override
    public int useTimeTicks() {
        return 10;
    }

    public BaseProjectileSpell() {
        super();
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

}