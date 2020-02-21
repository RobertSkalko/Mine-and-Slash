package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ILevel;
import net.minecraft.potion.EffectInstance;

import java.util.List;

public interface IDefaultApplyStatPotion extends IApplyStatPotion, ILevel {

    List<StatModData> statsMods();

    @Override
    default void applyStats(EntityCap.UnitData data, EffectInstance instance) {
        for (StatModData statsMod : statsMods()) {
            statsMod.useOnPlayer(data, this.getLevel());
        }
    }

}
