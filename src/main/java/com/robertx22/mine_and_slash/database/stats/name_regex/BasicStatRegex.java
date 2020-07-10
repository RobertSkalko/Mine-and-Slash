package com.robertx22.mine_and_slash.database.stats.name_regex;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;

public class BasicStatRegex extends StatNameRegex {

    @Override
    public String getStatNameRegex(ModType type, Stat stat) {

        if (type == ModType.FLAT) {
            if (!stat.UsesSecondValue()) {
                return VALUE + " " + NAME;
            } else {
                return "Adds " + MIN_VALUE + " to " + MAX_VALUE + " " + NAME;
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
