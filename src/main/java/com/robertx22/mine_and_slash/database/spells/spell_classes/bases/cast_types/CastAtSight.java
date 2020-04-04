package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CastAtSight extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {
        World world = ctx.caster.world;

        RayTraceResult ray = ctx.caster.pick(10D, 0.0F, false);

        Vec3d pos = ray.getHitVec();

        if (ctx.caster instanceof PlayerEntity == false) {
            pos = ctx.caster.getPositionVector();
        }

        Entity en = SpellUtils.getSpellEntity(ctx.spell.getImmutableConfigs()
            .newEntitySummoner()
            .apply(world), ctx.spell, ctx.caster);

        en.setPosition(pos.x, pos.y, pos.z);

        ctx.caster.world.addEntity(en);
        return true;
    }
}
