package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.crafting.bases.IRerollable;
import com.robertx22.database.lists.Suffixes;
import com.robertx22.gearitem.BaseAffix;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.gearitem.Suffix;
import com.robertx22.generation.StatGen;
import com.robertx22.saveclasses.abstractclasses.AffixData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.util.text.TextFormatting;

public class SuffixData extends AffixData implements Serializable, ITooltipList, IRerollable {

	private static final long serialVersionUID = 8802998468539898482L;

	public SuffixData() {

	}

	public SuffixData(GearItemData gear, String affixname, List<Integer> percents) {
		super();
		this.baseAffix = affixname;
		this.percents = percents;
	}

	@Override
	public boolean IfRerollFully() {
		return this.setRerollFully;
	}

	@Override
	public void SetRerollNumbers(boolean bool) {
		this.setRerollNumbers = bool;
	}

	@Override
	public boolean IfRerollNumbers() {

		return this.setRerollNumbers;
	}

	@Override
	public void RerollFully(GearItemData gear) {

		this.setRerollFully = false;

		List<IWeighted> list = ListUtils.CollectionToList(gear.GetBaseGearType().PossibleSuffixes());
		Suffix suffix = (Suffix) RandomUtils.WeightedRandom(list);

		baseAffix = suffix.Name();

		RerollNumbers(gear);

	}

	@Override
	public void RerollNumbers(GearItemData gear) {
		this.setRerollNumbers = false;

		percents = new ArrayList<Integer>();

		for (StatMod mod : BaseAffix().StatMods()) {
			percents.add(StatGen.GenPercent(gear.GetRarity()));
		}

	}

	@Override
	public BaseAffix BaseAffix() {
		return Suffixes.All.get(baseAffix);
	}

	@Override
	public List<String> GetTooltipString(GearItemData gear) {

		BaseAffix affix = BaseAffix();

		List<String> list = new ArrayList<String>();

		list.add(TextFormatting.LIGHT_PURPLE + "Suffix: " + affix.Name());

		for (StatModData data : this.GetAllStats(gear)) {

			list.add(data.GetTooltipString(gear));
		}

		return list;

	}

}
