package com.robertx22.mine_and_slash.vanilla_mc.potion_effects.bases;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public interface IOnBasicAttackedPotion {
    void onBasicAttacked(EffectInstance instance, LivingEntity source, LivingEntity target);
}
