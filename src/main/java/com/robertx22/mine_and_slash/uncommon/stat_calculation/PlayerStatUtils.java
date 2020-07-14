package com.robertx22.mine_and_slash.uncommon.stat_calculation;

import com.robertx22.mine_and_slash.config.base_player_stat.BasePlayerStatContainer;
import com.robertx22.mine_and_slash.saveclasses.unit.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {
        BasePlayerStatContainer.INSTANCE.applyStats(data);
    }

}
