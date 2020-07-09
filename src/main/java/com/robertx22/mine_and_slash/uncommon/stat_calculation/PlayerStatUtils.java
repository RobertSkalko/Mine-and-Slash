package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.base_player_stat.BasePlayerStatContainer;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import net.minecraft.entity.Entity;

import java.util.List;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {
        BasePlayerStatContainer.INSTANCE.applyStats(data);
    }

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears, UnitData unitdata) {

        for (GearItemData gear : gears) {

            if (!gear.isIdentified()) {
                continue;
            }

            gear.GetAllStats()
                .forEach(x -> {
                    x.applyStats(unitdata);
                });

        }

    }

}
