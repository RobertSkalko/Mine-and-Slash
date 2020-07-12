package com.robertx22.mine_and_slash.database.stats.name_regex;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

public class BasicStatRegex extends StatNameRegex {

    @Override
    public String getStatNameRegex(ModType type, Stat stat) {

        if (type == ModType.FLAT) {

            String adds = "";

            if (stat.UsesSecondValue() || stat.isLocal()) {
                adds = "Adds ";
            }

            if (stat.UsesSecondValue()) {

                return adds + MIN_VALUE + " to " + MAX_VALUE + " " + NAME;
            } else {
                return adds + VALUE + " To " + NAME;
            }
        }
        if (type == ModType.LOCAL_INCREASE) {
            return VALUE + " Increased " + NAME;
        }
        if (type == ModType.GLOBAL_INCREASE) {
            return VALUE + " To Global " + NAME;
        }

        return null;

    }
}
