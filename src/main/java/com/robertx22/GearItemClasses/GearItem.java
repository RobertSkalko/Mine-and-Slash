package com.robertx22.GearItemClasses;

import java.util.ArrayList;
import java.util.List;
import com.robertx22.constants.Tags;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import com.google.gson.*;

/* 
 	GearItem gear = new Gson().fromJson(stack.getTagCompound().getString("Data"),GearItem.class);
		
	stack.getTagCompound().setString("Data", new Gson().toJson(gear)); 
 */

public class GearItem {

	public GearItem(ItemStack stack) {
	
		GearItem gear = new Gson().fromJson(stack.getTagCompound().getString("Data"),GearItem.class);
		
		stack.getTagCompound().setString("Data", new Gson().toJson(gear));
		
	}
	
	
	
	public Double GetStat(String Name) {
		return null;
		/*
		  if (stack == null || !stack.hasTagCompound()) {
              return (double) 0;
          }
		  
		  
          ArrayList<NBTTagCompound> list = new ArrayList<NBTTagCompound>();
          list.add(stack.getTagCompound().getCompoundTag(Tags.Suffix));
          list.add(stack.getTagCompound().getCompoundTag(Tags.Prefix));         
          list.add(stack.getTagCompound().getCompoundTag(Tags.PrimaryStats));         
          list.add(stack.getTagCompound().getCompoundTag(Tags.SecondaryStats)); 
          list.add(stack.getTagCompound().getCompoundTag(Tags.ENCHANTS));
          list.add(stack.getTagCompound().getCompoundTag(Tags.SOCKETS));
          
          Double number = (double) 0; 		
          
          for (NBTTagCompound nbt : list) {
        	  
        	  if (nbt.hasKey(Name)) {
                  number += nbt.getInteger(Name);
              } 
        	  
          }
          
          return number;
		*/
	}
	
	
}
