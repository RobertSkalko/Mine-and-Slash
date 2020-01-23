package com.robertx22.mine_and_slash.potion_effects.bases;

import net.minecraft.entity.LivingEntity;

public interface IOnBasicAttackedPotion {
    void onBasicAttacked(LivingEntity source, LivingEntity target);
}
