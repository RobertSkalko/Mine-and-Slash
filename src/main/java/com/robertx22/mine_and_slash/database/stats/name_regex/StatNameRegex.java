package com.robertx22.mine_and_slash.database.stats.name_regex;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;

public abstract class StatNameRegex {

    public static StatNameRegex BASIC = new BasicStatRegex();

    public static String VALUE = "[N]";

    public static String MIN_VALUE = "[N1]";
    public static String MAX_VALUE = "[N2]";

    public static String NAME = "[NAME]";

    public abstract String getStatNameRegex(StatModTypes type);

    public String translate(StatModTypes type, float v1, float v2, Stat stat) {

        String percent = "";

        String plusminus = v1 > 0 ? "+" : "-";

        if (type == StatModTypes.LOCAL_INCREASE || type == StatModTypes.GLOBAL_INCREASE || stat.IsPercent()) {
            percent = "%";
        }

        String str = getStatNameRegex(type);

        if (stat.UsesSecondValue()) {
            str = str.replace(MIN_VALUE, plusminus + v1 + percent);
            str = str.replace(MAX_VALUE, plusminus + v2 + percent);
        } else {
            str = str.replace(VALUE, plusminus + v1 + percent);
        }

        str = str.replace(NAME, CLOC.translate(stat.locName()));

        return str;

    }
}
