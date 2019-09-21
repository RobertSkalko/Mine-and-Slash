package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.*;
import net.minecraft.util.text.TextFormatting;

public enum LvlPointStat {

    DEXTERITY(new Dexterity(), "DEX", TextFormatting.DARK_GREEN),
    VITALITY(new Vitality(), "VIT", TextFormatting.RED),
    WISDOM(new Wisdom(), "WIS", TextFormatting.AQUA),
    STAMINA(new Stamina(), "STA", TextFormatting.GREEN),
    STRENGTH(new Strength(), "STR", TextFormatting.GOLD),
    INTELLIGENCE(new Intelligence(), "INT", TextFormatting.BLUE);

    LvlPointStat(Stat stat, String shortname, TextFormatting format) {
        this.formatting = format;
        this.statguid = stat.GUID();
        this.shortName = shortname;
    }

    public TextFormatting formatting;
    public String shortName;
    public String statguid;

}
