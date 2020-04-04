package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.ProjectileCastOptions;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CastProjectile extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {
        World world = ctx.caster.world;

        ProjectileCastOptions builder = new ProjectileCastOptions(ctx);
        builder.projectilesAmount = (int) ctx.finishedConfig.projectileCount;
        builder.shootSpeed = ctx.finishedConfig.shootSpeed;
        builder.apart = 75;
        builder.cast();

        if (ctx.spell.getImmutableConfigs().sound != null) {
            ctx.caster.world.playMovingSound(null, ctx.caster, ctx.spell.getImmutableConfigs().sound, SoundCategory.HOSTILE, 1.0F, 1.0F);
        }
        return true;
    }
}
