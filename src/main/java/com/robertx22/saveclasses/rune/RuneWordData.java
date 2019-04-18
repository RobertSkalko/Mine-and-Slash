package com.robertx22.saveclasses.rune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.rarities.RuneRarity;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.RuneWords;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class RuneWordData implements IStatsContainer, ITooltipList {

    @Store
    public String name;

    @Store
    public int level;

    @Store
    public List<StatModData> Mods = new ArrayList<StatModData>();

    @Store
    public int rarity;

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
	return Arrays.asList(new LevelAndStats(Mods, this.level));
    }

    public RuneWordData() {

    }

    public RuneWordData(RunesData data, RuneWord word) {

	level = data.getAverageLevel();

	int percent = data.getAveragePercents();

	name = word.GUID();

	rarity = data.getAverageRarity();

	Mods.clear();

	for (StatMod mod : word.mods()) {
	    Mods.add(StatModData.Load(mod, percent));
	}

    }

    public RuneWord getRuneWord() {
	return RuneWords.All.get(name);
    }

    public RuneRarity getRarity() {
	return Rarities.Runes.get(rarity);
    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {
	List<String> list = new ArrayList();

	RuneRarity rar = this.getRarity();

	RuneWord word = getRuneWord();

	list.add(rar.Color() + "Rune Word: " + word.locName().toUpperCase());
	// list.add("");

	for (StatModData mod : this.Mods) {
	    list.addAll(mod.GetTooltipString(rar.StatPercents(), level, true));
	}

	return list;
    }

}
