package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.runewords.base.RuneWord;
import com.robertx22.database.runewords.base.slots_2.RuneWordStone;
import com.robertx22.database.runewords.base.slots_5.RuneWordHoming;
import com.robertx22.saveclasses.rune.RunesData;

public class RuneWords {
    public static HashMap<String, RuneWord> All = new HashMap<String, RuneWord>() {
	{
	    {

		put(new RuneWordStone().name(), new RuneWordStone());
		put(new RuneWordHoming().name(), new RuneWordHoming());

	    }
	}
    };

    public static RuneWord findMatching(RunesData data) {

	for (RuneWord runeword : All.values()) {

	    if (runeword.runesMatch(data)) {
		return runeword;
	    }

	}
	return null;

    }

}
