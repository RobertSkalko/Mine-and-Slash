package com.robertx22.gearitem;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.AffixData;
import com.robertx22.saveclasses.StatModData;

public class GearItem implements IStatsContainer {

	public String name;
	public int level;

	public AffixData suffix;
	public AffixData prefix;

	@Override
	public List<StatModData> GetAllStats() {

		List<StatModData> datas = new ArrayList<StatModData>();

		if (suffix != null) {
			datas.addAll(suffix.GetAllStats());
		}
		if (prefix != null) {
			datas.addAll(prefix.GetAllStats());
		}
		return datas;
	}

}
