package com.robertx22.saveclasses.rune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class RunesData implements ITooltipList, IStatsContainer {

    @Store
    public int level = 1;

    @Store
    public List<InsertedRuneData> runes = new ArrayList<InsertedRuneData>();

    @Store
    public List<RuneWordData> runewords = new ArrayList<RuneWordData>();

    @Store
    public int capacity = 1;

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

	List<LevelAndStats> list = new ArrayList<LevelAndStats>();
	for (InsertedRuneData rune : runes) {
	    list.addAll(rune.GetAllStats(level));
	}

	for (RuneWordData word : runewords) {
	    list.addAll(word.GetAllStats(level));
	}
	return list;
    }

    public String getRemainingRuneWordCombo() {

	String text = "";

	for (InsertedRuneData item : runes) {
	    if (item.usedForRuneWord.length() == 0) {
		text += item.rune.toUpperCase();
	    }
	}
	return text;
    }

    public void insert(RuneItemData rune, GearItemData gear) {

	this.runes.add(new InsertedRuneData(rune.level, rune.name, Arrays.asList(rune.getModFor(gear)), rune.rarity));

    }

    public boolean canAwakenRuneWord(RuneWord word) {

	String text = "";

	for (int i = 0; i < word.size(); i++) {
	    for (InsertedRuneData inserted : runes) {
		if (inserted.usedForRuneWord.length() == 0 && inserted.rune.equals(word.runes().get(i).name())) {

		    text += word.runes().get(i).name();

		    break;
		}
	    }

	}

	return text.toUpperCase().equals(word.getRuneWordCombo().toUpperCase());

    }

    public boolean AwakenRuneWord(String word) {
	RuneWord runeword = RuneWords.All.get(word);

	if (runeword != null) {

	    this.runewords.add(new RuneWordData(this, runeword));

	    for (int i = 0; i < runeword.size(); i++) {
		for (InsertedRuneData inserted : runes) {
		    if (inserted.usedForRuneWord.length() == 0
			    && inserted.rune.equals(runeword.runes().get(i).name())) {

			inserted.usedForRuneWord = runeword.GUID();
			break;
		    }
		}

	    }
	    return true;

	}
	return false;
    }

    public int getAveragePercents() {
	int per = 0;

	for (InsertedRuneData rune : runes) {

	    per += rune.getAveragePercents();
	}
	per = per / runes.size();

	return per;

    }

    public int getAverageLevel() {
	int per = 0;

	for (InsertedRuneData rune : runes) {

	    per += rune.level;
	}
	per = per / runes.size();

	return per;

    }

    public int getAverageRarity() {
	int per = 0;

	for (InsertedRuneData rune : runes) {

	    per += rune.rarity;
	}
	per = per / runes.size();

	return per;

    }

    public boolean canFit(GearItemData gear, RuneItemData rune) {
	return this.runes.size() < capacity && gear.level >= rune.level && !alreadyContains(rune);
    }

    public boolean alreadyContains(RuneItemData rune) {

	for (InsertedRuneData r : runes) {
	    if (r.rune.equals(rune.name)) {
		return true;
	    }
	}
	return false;

    }

    public void clearRunes() {
	this.runes.clear();
    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList();

	list.add("");

	String txt = "Runes: ";

	list.add(txt);

	for (InsertedRuneData rune : runes) {

	    list.addAll(rune.GetTooltipString(gear));
	}

	int empty = capacity - runes.size();

	for (int i = 0; i < empty; i++) {

	    list.add("Rune: [ Empty ]");

	}

	list.add("");

	if (this.runewords != null && runewords.size() > 0) {
	    for (RuneWordData word : runewords) {
		list.addAll(word.GetTooltipString(gear));
	    }
	}

	return list;
    }

}
