package com.robertx22.saveclasses.gearitem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.util.text.TextFormatting;

@Storable
public class GearTypeStatsData implements ITooltipList, IRerollable, IStatsContainer {

    public GearTypeStatsData() {

    }

    @Store
    public String geartypeGUID;
    @Store
    public List<Integer> percents = new ArrayList<Integer>();

    public GearTypeStatsData(String GUID) {
	this.geartypeGUID = GUID;
    }

    @Override
    public void RerollFully(GearItemData gear) {
	this.RerollNumbers(gear);
    }

    @Override
    public void RerollNumbers(GearItemData gear) {

	percents.clear();

	for (StatMod mod : this.getGearType().slotTypeStats()) {
	    percents.add(StatGen.GenPercent(gear.GetRarity()));
	}

    }

    @Override
    public List<String> GetTooltipString(GearItemData gear) {

	List<String> list = new ArrayList<String>();

	list.add(TextFormatting.GREEN + getGearType().locName() + " " + CLOC.word("stats") + ": ");

	for (LevelAndStats part : this.GetAllStats(gear.level)) {
	    for (StatModData data : part.mods) {
		list.addAll(data.GetTooltipString(gear.GetRarity().StatPercents(), part.level, true));
	    }
	}

	return list;

    }

    public GearItemSlot getGearType() {
	return GearTypes.All.get(this.geartypeGUID);
    }

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
	GearItemSlot slot = getGearType();

	List<StatModData> list = new ArrayList<StatModData>();

	for (int i = 0; i < slot.slotTypeStats().size(); i++) {

	    StatMod mod = slot.slotTypeStats().get(i);

	    list.add(StatModData.Load(mod, percents.get(i)));
	}

	return Arrays.asList(new LevelAndStats(list, level));
    }

}