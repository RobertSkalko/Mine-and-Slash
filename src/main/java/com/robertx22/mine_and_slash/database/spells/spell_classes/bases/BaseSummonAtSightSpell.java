package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class BaseSummonAtSightSpell extends BaseSpell {

    public abstract Entity newEntity(World world);

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        World world = caster.world;

        RayTraceResult ray = caster.pick(10D, 0.0F, false);

        Vec3d pos = ray.getHitVec();

        Entity en = SpellUtils.getSpellEntity(newEntity(world), this, caster);

        en.setPosition(pos.x, pos.y, pos.z);

        caster.world.addEntity(en);

        return true;
    }

}

