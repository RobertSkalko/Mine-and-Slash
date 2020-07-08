package com.robertx22.mine_and_slash.database.stats.name_regex;

import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class BasicStatRegex extends StatNameRegex {

    @Override
    public String getStatNameRegex(StatModTypes type) {

        if (type == StatModTypes.Flat) {
            return VALUE + " " + NAME;
        }
        if (type == StatModTypes.LOCAL_INCREASE) {
            return VALUE + " Increased " + NAME;
        }
        if (type == StatModTypes.GLOBAL_INCREASE) {
            return VALUE + " To Global " + NAME;
        }

        return null;

    }
}
