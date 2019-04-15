package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.gearitem.StatGroupData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class RunesData extends StatGroupData implements ITooltipList {

    @Store
    public List<String> runes = new ArrayList<String>();

    @Store
    public List<StatModData> runeword = new ArrayList<StatModData>();

    @Store
    public String runeword_name;

    @Store
    public int capacity = 1;

    @Override
    public List<StatModData> GetAllStats(int level) {

	List<StatModData> list = new ArrayList<StatModData>(Mods);
	list.addAll(runeword);

	return list;
    }

    public void insert(RuneItemData rune, GearItemData gear) {

	this.Mods.add(rune.getModFor(gear));
	this.runes.add(rune.name);

	if (this.canFit() == false) { // max runes
	    // create runeword
	}

    }

    public boolean canFit() {
	return this.Mods.size() < capacity;
    }

    public void clearRunes() {
	this.Mods.clear();
	this.runeword.clear();
    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList();

	list.add("");

	if (runes.size() > 0) {

	    String txt = "Runes: ";

	    for (int i = 0; i < runes.size(); i++) {

		txt += runes.get(i);

		if (i < runes.size() - 1) {
		    txt += " + ";
		}
	    }
	    list.add(txt);

	}

	for (StatModData mod : this.Mods) {
	    list.addAll(mod.GetTooltipString(gear.GetRarity().StatPercents(), gear.level, true));
	}

	list.add("");

	list.add("Rune Slots: " + this.capacity);

	return list;
    }

}
