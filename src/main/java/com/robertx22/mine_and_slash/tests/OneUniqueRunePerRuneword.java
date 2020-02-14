package com.robertx22.mine_and_slash.tests;

import com.robertx22.mine_and_slash.database.items.runes.base.BaseUniqueRuneItem;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

public class OneUniqueRunePerRuneword {

    public static void test() {

        SlashRegistry.RuneWords().getList().forEach(runeword -> {
            if (runeword.runes().stream().filter(rune -> rune instanceof BaseUniqueRuneItem).count() > 1) {
                throw new RuntimeException(
                        runeword.GUID() + " needs more than 1 unique rune, runewords should not be able to require " +
                                "more than 1.!");
            }
        });

    }
}
