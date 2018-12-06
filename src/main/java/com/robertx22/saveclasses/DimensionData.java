package com.robertx22.saveclasses;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

@Storable
public class DimensionData {

    public DimensionData() {

    }

    public DimensionData(String name, String suffix, int id, IWP theclass) {

	this.Name = name;
	this.suffix = suffix;
	this.ID = id;
	this.theclass = theclass.GUID();
    }

    @Store
    public String Name;
    @Store
    public int ID;
    @Store
    public String theclass;
    @Store
    public String suffix;

    public DimensionType getDimensionType() {

	try {
	    return DimensionType.register(Name, suffix, ID,
		    (Class<? extends WorldProvider>) WorldProviders.All.get(theclass).getClass(), false);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return DimensionType.OVERWORLD;
    }

}
