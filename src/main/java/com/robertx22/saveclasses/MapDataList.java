package com.robertx22.saveclasses;

import java.util.HashMap;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class MapDataList {

	@Store
	public HashMap<Integer, DimensionData> dimDatas = new HashMap<Integer, DimensionData>();

}
