package com.robertx22.saveclasses.rune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.StatGroupData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class InsertedRuneData extends StatGroupData implements ITooltipList {

    public InsertedRuneData() {

    }

    public InsertedRuneData(int level, String name, List<StatModData> mods) {
	this.level = level;
	this.rune = name;
	this.Mods = mods;
    }

    @Store
    public int level = 1;

    @Store
    public String rune;

    @Store
    public int rarity;

    public RuneRarity getRarity() {
	return Rarities.Runes.get(rarity);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {

	List<StatModData> list = new ArrayList<StatModData>(Mods);
	list.addAll(this.Mods);

	return Arrays.asList(new LevelAndStats(list, this.level));
    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {
	List<String> list = new ArrayList();

	for (StatModData mod : this.Mods) {
	    list.addAll(mod.GetTooltipString(this.getRarity().StatPercents(), level, true));
	}

	return list;
    }

}
