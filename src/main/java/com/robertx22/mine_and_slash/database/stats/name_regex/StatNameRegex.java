package com.robertx22.mine_and_slash.database.stats.name_regex;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import net.minecraft.util.text.TextFormatting;

public abstract class StatNameRegex {

    public static StatNameRegex BASIC = new BasicStatRegex();
    public static StatNameRegex BASIC_LOCAL = new BasicLocalStatRegex();

    public static String VALUE = "VALUE";

    public static String MIN_VALUE = "MIN_VALUE";
    public static String MAX_VALUE = "MAX_VALUE";

    public static String NAME = "STAT_NAME";

    static TextFormatting TEXT_COLOR = TextFormatting.GRAY;
    static TextFormatting NUMBER_COLOR = TextFormatting.GREEN;

    public abstract String getStatNameRegex(ModType type, Stat stat);

    public String translate(ModType type, float v1, float v2, Stat stat) {

        String v1s = NumberUtils.format(v1);
        String v2s = NumberUtils.format(v2);

        String percent = "";

        String plusminus = v1 > 0 ? "+" : "";

        if (stat.UsesSecondValue()) {
            plusminus = "";
        }

        if (type == ModType.LOCAL_INCREASE || type == ModType.GLOBAL_INCREASE || stat.IsPercent()) {
            percent = "%";
        }

        String str = TEXT_COLOR + getStatNameRegex(type, stat);

        if (type == ModType.FLAT && stat.UsesSecondValue()) {
            str = str.replace(MIN_VALUE, NUMBER_COLOR + plusminus + v1s + percent + TEXT_COLOR);
            str = str.replace(MAX_VALUE, NUMBER_COLOR + plusminus + v2s + percent + TEXT_COLOR);
        } else {
            str = str.replace(VALUE, NUMBER_COLOR + "" + plusminus + v1s + percent + TextFormatting.RESET + TEXT_COLOR);
        }

        str = str.replace(NAME, CLOC.translate(stat.locName()));

        return str;

    }
}
