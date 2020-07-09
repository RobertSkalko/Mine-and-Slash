package com.robertx22.mine_and_slash.database.stats.name_regex;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import net.minecraft.util.text.TextFormatting;

public abstract class StatNameRegex {

    public static StatNameRegex BASIC = new BasicStatRegex();

    public static String VALUE = "[N]";

    public static String MIN_VALUE = "[N1]";
    public static String MAX_VALUE = "[N2]";

    public static String NAME = "[NAME]";

    static TextFormatting TEXT_COLOR = TextFormatting.GRAY;
    static TextFormatting NUMBER_COLOR = TextFormatting.GREEN;

    public abstract String getStatNameRegex(StatModTypes type, Stat stat);

    public String translate(StatModTypes type, float v1, float v2, Stat stat) {

        String v1s = NumberUtils.formatNumber(v1);
        String v2s = NumberUtils.formatNumber(v2);

        String percent = "";

        String plusminus = v1 > 0 ? "+" : "-";

        if (stat.UsesSecondValue()) {
            plusminus = "";
        }

        if (type == StatModTypes.LOCAL_INCREASE || type == StatModTypes.GLOBAL_INCREASE || stat.IsPercent()) {
            percent = "%";
        }

        String str = TEXT_COLOR + getStatNameRegex(type, stat);

        if (type == StatModTypes.Flat && stat.UsesSecondValue()) {
            str = str.replace(MIN_VALUE, NUMBER_COLOR + plusminus + v1s + percent + TextFormatting.RESET + TEXT_COLOR);
            str = str.replace(MAX_VALUE, NUMBER_COLOR + plusminus + v2s + percent + TextFormatting.RESET + TEXT_COLOR);
        } else {
            str = str.replace(VALUE, NUMBER_COLOR + plusminus + v1s + percent + TextFormatting.RESET + TEXT_COLOR);
        }

        str = str.replace(NAME, CLOC.translate(stat.locName()));

        return str;

    }
}
