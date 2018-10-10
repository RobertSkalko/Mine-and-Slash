package com.robertx22.Stats.Types;

import com.robertx22.Enums.Elements;
import com.robertx22.Enums.StatRefs;
import com.robertx22.Stats.FillableStat;

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
