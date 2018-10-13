package com.robertx22.generation;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.SuffixData;

public class SuffixGen {

	public static SuffixData Gen(GearItemData gear) {

		gear.suffix = new SuffixData();
		gear.suffix.RerollFully(gear);

		return null;

	}

}
