package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

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
        return 8;
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    public abstract AbstractArrowEntity newEntity(World world);

    public abstract SoundEvent getShootSound();

    public float getShootSpeed() {
        return 2F;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        World world = caster.world;

        AbstractArrowEntity en = SpellUtils.getSpellEntity(newEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, getShootSpeed());
        caster.world.addEntity(en);

        if (getShootSound() != null) {
            caster.world.playMovingSound(null, en, getShootSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
        }

        return true;
    }
}