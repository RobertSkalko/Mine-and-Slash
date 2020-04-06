package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public class PotionContext extends SynergyContext {

    public LivingEntity caster, target;

    public EffectInstance effectInstance;

    public PotionContext(LivingEntity caster, LivingEntity target, EffectInstance effectInstance) {
        this.caster = caster;
        this.target = target;

        this.effectInstance = effectInstance;

        this.spellsCap = Load.spells(caster);
    }
}
