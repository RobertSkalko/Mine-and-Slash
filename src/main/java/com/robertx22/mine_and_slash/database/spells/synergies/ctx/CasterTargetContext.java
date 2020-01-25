package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import net.minecraft.entity.LivingEntity;

public class CasterTargetContext extends SynergyContext {
    public LivingEntity caster, target;

    public CasterTargetContext(LivingEntity caster, LivingEntity target) {
        this.caster = caster;
        this.target = target;
    }
}
