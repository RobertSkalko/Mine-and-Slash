package com.robertx22.mine_and_slash.potion_effects;

import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.potion.EffectInstance;

public interface IStatPotion {
    void applyStats(EntityCap.UnitData data, EffectInstance instance);
}
