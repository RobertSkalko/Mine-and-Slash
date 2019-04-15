package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import info.loenwind.autosave.annotations.Storable;

@Storable
public class PrimaryStatsData extends StatGroupData implements ITooltipList, IRerollable {

    public PrimaryStatsData() {

    }

    @Override
    public void RerollFully(GearItemData gear) {

	this.Mods = new ArrayList<StatModData>();

	List<IWeighted> possibleStats = ListUtils.CollectionToList(gear.GetBaseGearType().PrimaryStats());

	StatMod mod = (StatMod) RandomUtils.WeightedRandom(possibleStats);

	StatModData moddata = StatModData.NewRandom(gear, mod);

	this.Mods.add(moddata);

    }

    @Override
    public void RerollNumbers(GearItemData gear) {

	for (StatModData data : this.Mods) {
	    data.percent = StatGen.GenPercent(gear.GetRarity());
	}

    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList<String>();

	list.add(CLOC.word("primary_stats") + ":");

	for (LevelAndStats part : this.GetAllStats(gear.level)) {
	    for (StatModData data : part.mods) {
		list.addAll(data.GetTooltipString(gear.GetRarity().StatPercents(), part.level, true));
	    }
	}

	return list;

    }

}
