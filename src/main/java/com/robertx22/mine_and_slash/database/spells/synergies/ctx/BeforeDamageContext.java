package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.entity.LivingEntity;

public class BeforeDamageContext extends SynergyContext {

    public LivingEntity caster, target;

    public DamageEffect dmg;

    public BeforeDamageContext(LivingEntity caster, LivingEntity target, DamageEffect dmg) {
        this.caster = caster;
        this.target = target;
        this.dmg = dmg;
    }
}
