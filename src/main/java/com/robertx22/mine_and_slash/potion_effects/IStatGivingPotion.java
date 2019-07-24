package com.robertx22.mine_and_slash.potion_effects;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public interface IStatGivingPotion {
    List<StatModData> getStats(EffectInstance instance);
}
