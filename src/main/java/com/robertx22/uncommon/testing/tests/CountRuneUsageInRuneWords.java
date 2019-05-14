package com.robertx22.uncommon.testing.tests;

import java.util.HashMap;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.items.runes.base.BaseRuneItem;

public class CountRuneUsageInRuneWords {

    public static void doit() {

	HashMap<String, Integer> all = new HashMap();

	for (RuneWord word : RuneWords.All.values()) {

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
