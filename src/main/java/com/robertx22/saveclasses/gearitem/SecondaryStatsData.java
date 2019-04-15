package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
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
import info.loenwind.autosave.annotations.Store;

@Storable
public class SecondaryStatsData extends StatGroupData implements Serializable, ITooltipList, IRerollable {

    private static final long serialVersionUID = 6149243047165372987L;

    public SecondaryStatsData() {

    }

    @Store
    public boolean AddedStat = false;

    @Override
    public void RerollFully(GearItemData gear) {

	this.Mods = new ArrayList<StatModData>();

	int Stats = RandomUtils.RandomRange(1, 3);

	List<IWeighted> possibleStats = ListUtils.CollectionToList(gear.GetBaseGearType().PossibleSecondaryStats());

	while (Stats > 0) {
	    StatMod mod = (StatMod) RandomUtils.WeightedRandom(possibleStats);
	    this.Mods.add(StatModData.NewRandom(gear, mod));
	    Stats--;

	}

    }

    public void AddStat(GearItemData gear) {
	StatMod mod = (StatMod) RandomUtils
		.WeightedRandom(ListUtils.CollectionToList(gear.GetBaseGearType().PossibleSecondaryStats()));

	gear.secondaryStats.Mods.add(StatModData.NewRandom(gear, mod));

	this.AddedStat = true;

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

	list.add(CLOC.word("secondary_stats") + ":");

	for (LevelAndStats part : this.GetAllStats(gear.level)) {
	    for (StatModData data : part.mods) {
		list.addAll(data.GetTooltipString(gear.GetRarity().StatPercents(), part.level, true));
	    }
	}

	return list;

    }

}
