package com.robertx22.database.stats.types;

import com.robertx22.enums.Elements;
import com.robertx22.enums.StatRefs;
import com.robertx22.stats.FillableStat;

public class Health extends FillableStat {

	@Override
	public String Name() {
		return "Health";
	}

	@Override
	public boolean ScalesToLevel() {
		return true;
	}

	@Override
	public Elements Element() {
		return null;
	}

	@Override
	public StatRefs StatRef() {
		return StatRefs.Health;
	}

}
