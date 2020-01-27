package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

public abstract class BaseProjectileSpell extends BaseSpell {

    @Override
    public int useTimeTicks() {
        return 10;
    }

    public BaseProjectileSpell() {
        super();
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

}