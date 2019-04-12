package com.robertx22.mmorpg.config;

import java.util.ArrayList;

public class DimensionsContainer {

    public DimensionsContainer(ArrayList<DimensionConfigs> list) {
	this.list = list;
    }

    ArrayList<DimensionConfigs> list = new ArrayList<DimensionConfigs>();

    public boolean hasConfig(int id) {

	for (DimensionConfigs c : list) {
	    if (id == c.DIMENSION_id) {
		return true;
	    }
	}
	return false;
    }

    public DimensionConfigs getConfig(int id) {

	for (DimensionConfigs c : list) {
	    if (id == c.DIMENSION_id) {
		return c;
	    }

	}
	return ModConfig.Dimensions.default_dim; // default
    }

}
