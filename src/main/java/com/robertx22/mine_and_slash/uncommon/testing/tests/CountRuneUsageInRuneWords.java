package com.robertx22.mine_and_slash.uncommon.testing.tests;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.HashMap;

public class CountRuneUsageInRuneWords {

    public static void doit() {

        HashMap<String, Integer> all = new HashMap();

        for (RuneWord word : SlashRegistry.RuneWords().getAll().values()) {

            for (BaseRuneItem rune : word.runes()) {

                int i = 0;
                if (all.containsKey(rune.name())) {
                    i = all.get(rune.name());
                }
                i++;
                all.put(rune.name(), i);

            }

        }

        System.out.println(all);
        // System.out.println(all.keySet());
        // System.out.println(all.entrySet());

    }
}
