package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapWorldData {
	@Store
	public List<String> joinedPlayerIDs = new ArrayList<String>();

}
