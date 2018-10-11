package com.robertx22.gearitem;

/* 
 	GearItem gear = new Gson().fromJson(stack.getTagCompound().getString("Data"),GearItem.class);
		
	stack.getTagCompound().setString("Data", new Gson().toJson(gear)); 
 */

public class GearItem {

	public String Name;

	public AffixData Suffix;
	public AffixData Prefix;

	public int GetStat(String Name) {

		return 0;
	}

}
