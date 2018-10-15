package com.robertx22.datasaving;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.robertx22.capability.EntityData;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Saving {

	private static final Class<? extends Unit> UnitClass = Unit.class;
	private static final Class<? extends GearItemData> GearClass = GearItemData.class; 

	private static String DataLocation = "PathOfMinecraftData";
	private static String StackData = "ItemStackData";
	private static Gson gson = new Gson();

	@SuppressWarnings("unchecked")
	public static <T> T Load(EntityLivingBase stack) {

		if (stack.hasCapability(EntityData.Data, null)) {
			try {
				return (T) gson.fromJson(stack.getCapability(EntityData.Data, null).getNBT().getString(DataLocation),
						UnitClass);
			} catch (JsonSyntaxException e) {

				e.printStackTrace();
			}
		} else {

			System.out.println("ERROR can't load capability!");

		}
		return null;

	}

	public static <T> void Save(EntityLivingBase stack, Object obj) {

		if (stack.hasCapability(EntityData.Data, null)) {

			try {
				stack.getCapability(EntityData.Data, null).getNBT().setString(DataLocation, gson.toJson(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("ERROR can't save capability!");
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T Load(ItemStack stack) {

		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(StackData)) {

			T obj = null;

			try {

				NBTTagCompound nbt = null;
				if (stack.getTagCompound() == null) {
					nbt = new NBTTagCompound();
				} else {
					nbt = stack.getTagCompound();
				}

				String str = nbt.getString(StackData);
				// System.out.println(type.toString());
				obj = (T) gson.fromJson(str, GearClass);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return obj;
		} else {
			return null;
		}
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
