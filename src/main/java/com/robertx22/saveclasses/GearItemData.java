package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.robertx22.gearitem.IStatsContainer;

public class GearItemData implements IStatsContainer, Serializable {

	private static final long serialVersionUID = -8327205425334275976L;

	public String name;
	public int level;

	public StatGroupData PrimaryStats;
	public StatGroupData SecondaryStats;

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
