package com.robertx22.stats;

import com.robertx22.classes.Unit;

public abstract class RangeStatMod extends StatMod {

	public abstract String RangeBaseClass();
	
	@Override
	public String NameAndValueText(Unit Source) {
		RangeStat basestat = GetBase(RangeBaseClass());

		return NameText() +  basestat.MinStat().GetValue(Source)+ "-" + basestat.MaxStat().GetValue(Source);
	}
}
