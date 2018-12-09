package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.uncommon.CLOC;

public abstract class Prefix extends BaseAffix {

    public Prefix() {

    }

    public String locName() {

	return CLOC.prefix(GUID().toLowerCase().replaceAll(" ", "_"));
    }
}
