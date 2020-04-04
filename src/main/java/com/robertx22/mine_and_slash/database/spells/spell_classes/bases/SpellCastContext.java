package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class SpellCastContext {

    public final LivingEntity caster;
    public final EntityCap.UnitData data;
    public final int ticksInUse;
    public final BaseSpell spell;
    public final SpellConfig config;
    public final boolean isLastCastTick;

    public SpellCastContext(LivingEntity caster, int ticksInUse, BaseSpell spell) {
        this.caster = caster;
        this.ticksInUse = ticksInUse;
        this.spell = spell;

        this.data = Load.Unit(caster);

        this.config = spell.getConfigCopy();

        this.isLastCastTick = config.castTimeTicks.getValueFor(this) == ticksInUse;
    }
}
