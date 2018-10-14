package com.robertx22.saving;

import com.google.gson.Gson;
import com.robertx22.capability.EntityData;
import com.robertx22.capability.StackData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class Saving {

	private static String DataLocation = "PathOfMinecraftData";
	private static Gson gson = new Gson();

	public static <T> T Load(EntityLivingBase stack, Class<?> type) {

		if (stack.hasCapability(EntityData.Data, null)) {
			return (T) gson.fromJson(stack.getCapability(EntityData.Data, null).getNBT().getString(DataLocation),
					type);
		} else {
			
			System.out.println("ERROR can't load capability!");
			// throw new Exception("Entity has no EntityData capability!");

		}
		return null;

	}

	public static <T> void Save(EntityLivingBase stack, Object obj) {

		if (stack.hasCapability(EntityData.Data, null)) {
			stack.getCapability(EntityData.Data, null).getNBT().setString(DataLocation, gson.toJson(obj));
		} else {
			System.out.println("ERROR can't save capability!");
			
			// throw new Exception("Entity has no EntityData capability!");
		}
	}
	
	
	public static <T> T Load(ItemStack stack, Class<?> type) {

		if (stack.hasCapability(StackData.Data, null)) {
			return (T) gson.fromJson(stack.getCapability(StackData.Data, null).getNBT().getString(DataLocation),
					type);
		} else {
			
			System.out.println("ERROR can't load capability!");
			// throw new Exception("Entity has no EntityData capability!");

		}
		return null;

	}

	public static <T> void Save(ItemStack stack, Object obj) {

		if (stack.hasCapability(StackData.Data, null)) {
			stack.getCapability(StackData.Data, null).getNBT().setString(DataLocation, gson.toJson(obj));
		} else {
			System.out.println("ERROR can't save capability!");
			
			// throw new Exception("Entity has no EntityData capability!");
		}

	}
}
