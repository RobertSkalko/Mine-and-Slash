package com.robertx22.saveclasses.rune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public int capacity = 1;

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

	List<LevelAndStats> list = new ArrayList<LevelAndStats>();
	for (InsertedRuneData rune : runes) {
	    list.addAll(rune.GetAllStats(level));
	}

	return list;
    }

    public void insert(RuneItemData rune, GearItemData gear) {

	this.runes.add(new InsertedRuneData(rune.level, rune.name, Arrays.asList(rune.getModFor(gear)), rune.rarity));

	// create runeword

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

	// list.add("Rune Slots: " + this.capacity);

	return list;
    }

}
