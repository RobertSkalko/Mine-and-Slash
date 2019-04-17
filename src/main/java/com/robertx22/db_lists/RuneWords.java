package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.runewords.slots_2.RuneWordScales;
import com.robertx22.database.runewords.slots_2.RuneWordStone;
import com.robertx22.database.runewords.slots_3.RuneWordRadiance;
import com.robertx22.database.runewords.slots_3.RuneWordThief;
import com.robertx22.database.runewords.slots_4.RuneWordMagician;
import com.robertx22.database.runewords.slots_4.RuneWordMountain;
import com.robertx22.database.runewords.slots_4.RuneWordZephyr;
import com.robertx22.database.runewords.slots_5.RuneWordGhost;
import com.robertx22.database.runewords.slots_5.RuneWordHoming;
import com.robertx22.database.runewords.slots_5.RuneWordLight;
import com.robertx22.saveclasses.rune.RunesData;

public class RuneWords {
    public static HashMap<String, RuneWord> All = new HashMap<String, RuneWord>() {
	{
	    {

		put(new RuneWordStone().name(), new RuneWordStone());
		put(new RuneWordHoming().name(), new RuneWordHoming());
		put(new RuneWordGhost().name(), new RuneWordGhost());

		put(new RuneWordScales().name(), new RuneWordScales());
		put(new RuneWordRadiance().name(), new RuneWordRadiance());
		put(new RuneWordThief().name(), new RuneWordThief());
		put(new RuneWordMagician().name(), new RuneWordMagician());
		put(new RuneWordMountain().name(), new RuneWordMountain());
		put(new RuneWordZephyr().name(), new RuneWordZephyr());
		put(new RuneWordLight().name(), new RuneWordLight());

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
