package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.database.lists.Prefixes;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.abstractclasses.AffixData;
import com.robertx22.saveclasses.gearitem.gear_bases.BaseAffix;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.gearitem.gear_bases.Prefix;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class PrefixData extends AffixData implements Serializable, ITooltipList, IRerollable {

	private static final long serialVersionUID = -110285627065158395L;

	public PrefixData() {

	}

	public PrefixData(GearItemData gear, String affixname, List<Integer> percents) {
		super();
		this.baseAffix = affixname;
		this.percents = percents;

	}

	@Override
	public void RerollFully(GearItemData gear) {

		List<IWeighted> list = ListUtils.CollectionToList(gear.GetBaseGearType().PossiblePrefixes());
		Prefix prefix = (Prefix) RandomUtils.WeightedRandom(list);

		baseAffix = prefix.Name();

		RerollNumbers(gear);

	}

	@Override
	public void RerollNumbers(GearItemData gear) {

		percents = new ArrayList<Integer>();

		for (StatMod mod : BaseAffix().StatMods()) {
			percents.add(StatGen.GenPercent(gear.GetRarity()));
		}

	}

	@Override
	public BaseAffix BaseAffix() {
		return Prefixes.All().get(baseAffix);
	}

	@Override
	public List<String> GetTooltipString(GearItemData gear) {

		BaseAffix affix = BaseAffix();

		List<String> list = new ArrayList<String>();

		list.add("Prefix: " + affix.Name());

		for (StatModData data : this.GetAllStats(gear.level)) {

			list.add(data.GetTooltipString(gear));
		}

		return list;

	}

}
