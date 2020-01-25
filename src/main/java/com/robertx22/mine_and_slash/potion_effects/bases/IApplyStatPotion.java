package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.potion.EffectInstance;

public interface IApplyStatPotion {
    void applyStats(EntityCap.UnitData data, EffectInstance instance);
}
