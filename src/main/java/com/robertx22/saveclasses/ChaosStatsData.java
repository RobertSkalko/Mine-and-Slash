package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.crafting.bases.IRerollable;
import com.robertx22.gearitem.ITooltipList;
import com.robertx22.saveclasses.abstractclasses.StatGroupData;
import com.robertx22.stats.StatMod;

import net.minecraft.util.text.TextFormatting;

public class ChaosStatsData extends StatGroupData implements Serializable, ITooltipList, IRerollable {

	private static final long serialVersionUID = -8272316157157669116L;

	public ChaosStatsData() {

	}

	public boolean IfCreate() {
		return this.setRerollFully;
	}

	@Override
	public List<String> GetTooltipString() {

		List<String> list = new ArrayList<String>();

		list.add(TextFormatting.RED + "Chaos Stats: ");

		for (StatModData data : this.GetAllStats()) {

			list.add(data.GetTooltipString());
		}

		return list;

	}

	@Override
	public boolean IfRerollFully() {
		return this.setRerollFully;
	}

	@Override
	public boolean IfRerollNumbers() {
		return false;
	}

	@Override
	public void RerollFully(GearItemData gear) {

		this.setRerollFully = false;

		this.level = gear.level;

		this.Mods = new ArrayList<StatModData>();

		for (StatMod mod : gear.GetBaseGearType().ChaosStats()) {

			StatModData moddata = StatModData.NewRandom(mod, gear.level);

			this.Mods.add(moddata);

		}

	}

	@Override
	public void RerollNumbers(GearItemData gear) {

	}

}
