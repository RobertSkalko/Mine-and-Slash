package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import net.minecraft.util.text.TextFormatting;

public enum LvlPointStat {

    DEXTERITY(Dexterity.INSTANCE, "DEX", TextFormatting.DARK_GREEN),
    VITALITY(Vitality.INSTANCE, "VIT", TextFormatting.RED),
    WISDOM(Wisdom.INSTANCE, "WIS", TextFormatting.AQUA),
    STAMINA(Stamina.INSTANCE, "STA", TextFormatting.GREEN),
    STRENGTH(Strength.INSTANCE, "STR", TextFormatting.GOLD),
    INTELLIGENCE(Intelligence.INSTANCE, "INT", TextFormatting.BLUE);

    LvlPointStat(Stat stat, String shortname, TextFormatting format) {
        this.formatting = format;
        this.statguid = stat.GUID();
        this.shortName = shortname;
    }

    public TextFormatting formatting;
    public String shortName;
    public String statguid;

}
