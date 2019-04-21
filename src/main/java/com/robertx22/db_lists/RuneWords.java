package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.runewords.slots_2.RuneWordBloom;
import com.robertx22.database.runewords.slots_2.RuneWordMagma;
import com.robertx22.database.runewords.slots_2.RuneWordScales;
import com.robertx22.database.runewords.slots_2.RuneWordStone;
import com.robertx22.database.runewords.slots_2.RuneWordZeal;
import com.robertx22.database.runewords.slots_3.RuneWordRadiance;
import com.robertx22.database.runewords.slots_3.RuneWordThief;
import com.robertx22.database.runewords.slots_4.RuneWordLimitless;
import com.robertx22.database.runewords.slots_4.RuneWordMagician;
import com.robertx22.database.runewords.slots_4.RuneWordMountain;
import com.robertx22.database.runewords.slots_4.RuneWordProfoundSea;
import com.robertx22.database.runewords.slots_4.RuneWordZephyr;
import com.robertx22.database.runewords.slots_5.RuneWordGhost;
import com.robertx22.database.runewords.slots_5.RuneWordHoming;
import com.robertx22.database.runewords.slots_5.RuneWordInfinity;
import com.robertx22.database.runewords.slots_5.RuneWordLight;
import com.robertx22.database.runewords.slots_5.RuneWordPurity;

public class RuneWords {
    public static HashMap<String, RuneWord> All = new HashMap<String, RuneWord>() {
	{
	    {

		put(new RuneWordStone().GUID(), new RuneWordStone());
		put(new RuneWordHoming().GUID(), new RuneWordHoming());
		put(new RuneWordGhost().GUID(), new RuneWordGhost());

		put(new RuneWordScales().GUID(), new RuneWordScales());
		put(new RuneWordRadiance().GUID(), new RuneWordRadiance());
		put(new RuneWordThief().GUID(), new RuneWordThief());
		put(new RuneWordMagician().GUID(), new RuneWordMagician());
		put(new RuneWordMountain().GUID(), new RuneWordMountain());
		put(new RuneWordZephyr().GUID(), new RuneWordZephyr());
		put(new RuneWordLight().GUID(), new RuneWordLight());

		put(new RuneWordMagma().GUID(), new RuneWordMagma());
		put(new RuneWordBloom().GUID(), new RuneWordBloom());
		put(new RuneWordZeal().GUID(), new RuneWordZeal());
		put(new RuneWordPurity().GUID(), new RuneWordPurity());
		put(new RuneWordInfinity().GUID(), new RuneWordInfinity());

		put(new RuneWordProfoundSea().GUID(), new RuneWordProfoundSea());
		put(new RuneWordLimitless().GUID(), new RuneWordLimitless());

	    }
	}
    };

}
