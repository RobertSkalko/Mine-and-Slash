package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import net.minecraft.entity.LivingEntity;

public class SpellCastContext {

    public final LivingEntity caster;
    public final int ticksInUse;

    public SpellCastContext(LivingEntity caster, int ticksInUse) {
        this.caster = caster;
        this.ticksInUse = ticksInUse;
    }
}
