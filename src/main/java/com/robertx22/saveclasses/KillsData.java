package com.robertx22.saveclasses;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class KillsData implements Comparable<KillsData> {

    public KillsData() {

    }

    public KillsData(String guid) {
	this.guid = guid;
    }

    @Store
    public Integer count = 0;
    @Store
    public String guid;

    @Override
    public int compareTo(KillsData o) {

	if (o != null && o.count != null && count != null) {
	    return count.compareTo(o.count);
	}
	return 0;
    }

    public boolean isValid() {

	return count != null && guid != null && guid.isEmpty() == false;
    }

}
