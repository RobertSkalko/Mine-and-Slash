package com.robertx22.saveclasses.gearitem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.crafting.bases.IRerollable;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.saveclasses.abstractclasses.StatGroupData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.util.text.TextFormatting;

public class ChaosStatsData extends StatGroupData implements Serializable, ITooltipList, IRerollable {

	private static final long serialVersionUID = -8272316157157669116L;

	public ChaosStatsData() {

	}

	@Override
	public List<String> GetTooltipString(GearItemData gear) {

		List<String> list = new ArrayList<String>();

		list.add(TextFormatting.RED + "Chaos Stats: ");

		for (StatModData data : this.GetAllStats(gear)) {

			list.add(data.GetTooltipString(gear));
		}

		return list;

	}

	@Override
	public void RerollFully(GearItemData gear) {

		this.Mods = new ArrayList<StatModData>();

		StatMod mod = (StatMod) RandomUtils
				.WeightedRandom(ListUtils.CollectionToList(gear.GetBaseGearType().ChaosStats()));

		StatModData moddata = StatModData.NewRandom(gear, mod);

		this.Mods.add(moddata);

	}

	@Override
	public void RerollNumbers(GearItemData gear) {

	}

}
