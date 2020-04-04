package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.ProjectileCastOptions;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CastProjectile extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {
        World world = ctx.caster.world;

        ProjectileCastOptions builder = new ProjectileCastOptions(ctx);
        builder.projectilesAmount = (int) ctx.config.projectileCount.getValueFor(ctx);
        builder.shootSpeed = ctx.config.shootSpeed.getValueFor(ctx);
        builder.apart = 75;
        builder.cast();

        if (ctx.config.sound != null) {
            ctx.caster.world.playMovingSound(null, ctx.caster, ctx.config.sound, SoundCategory.HOSTILE, 1.0F, 1.0F);
        }
        return true;
    }
}
