package com.robertx22.gearitem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import com.google.gson.*;
import com.robertx22.constants.Tags;

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
