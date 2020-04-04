package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public abstract class BaseProjectileSpell extends BaseSpell {

    @Override
    public LevelBased useTimeTicks() {
        return new LevelBased(10, 10);
    }

    public BaseProjectileSpell() {
        super();
    }

    @Override
    public int getManaCost() {
        return 5;
    }

    public abstract AbstractArrowEntity newEntity(World world);

    public LevelBased getShootSpeed() {
        return new LevelBased(1, 1.5F);
    }

    public LevelBased getProjectileCount() {
        return new LevelBased(1, 1);
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        World world = caster.world;

        AbstractArrowEntity en = SpellUtils.getSpellEntity(newEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, getShootSpeed());
        caster.world.addEntity(en);

        if (getCastSound() != null) {
            caster.world.playMovingSound(null, en, getCastSound(), SoundCategory.HOSTILE, 1.0F, 1.0F);
        }

        return true;
    }
}