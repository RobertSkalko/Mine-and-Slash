package com.robertx22.mine_and_slash.database.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.function.Function;

public class ProjectileCastOptions {

    BaseSpell spell;
    Function<World, Entity> projectile;
    LivingEntity caster;

    public float apart = 3;
    public float shootSpeed = 1;
    public int projectilesAmount = 1;
    SpellCastContext ctx;

    public ProjectileCastOptions(SpellCastContext ctx) {
        this.spell = ctx.spell;
        this.projectile = ctx.config.newEntitySummoner;
        this.caster = ctx.caster;
        this.ctx = ctx;
    }

    public void cast() {
        World world = caster.world;

        if (projectilesAmount > 1) {

            float addYaw = 0;

            for (int i = 0; i < projectilesAmount; i++) {

                if (i < projectilesAmount / 2) {
                    addYaw -= apart / projectilesAmount;
                } else if (i == projectilesAmount / 2) {
                    addYaw = 0;
                } else if (i > projectilesAmount / 2) {
                    addYaw += apart / projectilesAmount;
                }
                AbstractArrowEntity en = (AbstractArrowEntity) SpellUtils.getSpellEntity(projectile.apply(world), spell, caster);
                SpellUtils.setupProjectileForCasting(en, caster, shootSpeed, caster.rotationPitch,
                    caster.rotationYaw + addYaw
                );
                caster.world.addEntity(en);

                if (ctx.config.sound != null) {
                    ctx.caster.world.playMovingSound(null, en, ctx.config.sound, SoundCategory.HOSTILE, 1.0F, 1.0F);
                }

            }
        } else {
            AbstractArrowEntity en = (AbstractArrowEntity) SpellUtils.getSpellEntity(projectile.apply(world), spell, caster);
            SpellUtils.setupProjectileForCasting(en, caster, shootSpeed, caster.rotationPitch, caster.rotationYaw
            );
            caster.world.addEntity(en);

            if (ctx.config.sound != null) {
                ctx.caster.world.playMovingSound(null, en, ctx.config.sound, SoundCategory.HOSTILE, 1.0F, 1.0F);
            }
        }
    }

}
