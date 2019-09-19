package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.*;

public enum LvlPointStat {

    DEXTERITY(new Dexterity()),
    VITALITY(new Vitality()),
    WISDOM(new Wisdom()),
    STAMINA(new Stamina()),
    STRENGTH(new Strength()),
    INTELLIGENCE(new Intelligence());

    LvlPointStat(Stat stat) {
        this.statguid = stat.GUID();
    }

    public String statguid;

}
