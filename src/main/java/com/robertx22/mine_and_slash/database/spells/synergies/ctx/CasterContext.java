package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import net.minecraft.entity.LivingEntity;

public class CasterContext extends SynergyContext {

    public LivingEntity caster;

    public CasterContext(LivingEntity caster) {
        this.caster = caster;
    }
}
