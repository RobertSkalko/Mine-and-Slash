package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.proj.SeedEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class BaseSeedSpell extends BaseSpell implements IBlockSpawner {

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        World world = caster.world;
        Vec3d pos = caster.getPositionVector();
        SeedEntity en = SpellUtils.getSpellEntity(new SeedEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, 0.5F);
        caster.world.addEntity(en);

        caster.world.playMovingSound(null, en, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return true;
    }

}

