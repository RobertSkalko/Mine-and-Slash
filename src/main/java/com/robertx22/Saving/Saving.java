package com.robertx22.saving;

import com.google.gson.Gson;
import com.robertx22.capability.EntityData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Saving {

	private static String DataLocation = "PathOfMinecraftData";
	private static String StackData = "ItemStackData";
	private static Gson gson = new Gson();

	public static <T> T Load(EntityLivingBase stack, Class<?> type) {

		if (stack.hasCapability(EntityData.Data, null)) {
			return (T) gson.fromJson(stack.getCapability(EntityData.Data, null).getNBT().getString(DataLocation), type);
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

		T obj = null;

		try {

			NBTTagCompound nbt = null;
			if (stack.getTagCompound() == null) {
				nbt = new NBTTagCompound();
			} else {
				nbt = stack.getTagCompound();
			}

			String str = nbt.getString(StackData);
			obj = (T) gson.fromJson(str, type);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static <T> void Save(ItemStack stack, Object obj) {

		try {

			NBTTagCompound nbt = null;
			if (stack.getTagCompound() == null) {
				nbt = new NBTTagCompound();
			} else {
				nbt = stack.getTagCompound();
			}

			String json = gson.toJson(obj);

			nbt.setString(StackData, json);

			stack.setTagCompound(nbt);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
