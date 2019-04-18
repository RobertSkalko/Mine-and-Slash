package com.robertx22.generation.blueprints;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class AwakenRuneWordBlueprint {

    public String word = "";

    public RuneWord getWord() {

	if (RuneWords.All.containsKey(word) == false) {

	    RuneWord random = (RuneWord) RandomUtils.WeightedRandom(ListUtils.CollectionToList(RuneWords.All.values()));

	    word = random.GUID();

	}

	return RuneWords.All.get(word);

    }

}
