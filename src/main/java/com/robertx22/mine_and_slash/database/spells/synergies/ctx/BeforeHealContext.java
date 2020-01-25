package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.uncommon.effectdatas.HealEffect;
import net.minecraft.entity.LivingEntity;

public class BeforeHealContext extends SynergyContext {

    public LivingEntity caster, target;

    public HealEffect heal;

    public BeforeHealContext(LivingEntity caster, LivingEntity target, HealEffect heal) {
        this.caster = caster;
        this.target = target;
        this.heal = heal;
    }
}
