package com.robertx22.datasaving;

import com.robertx22.datasaving.bases.Gson;
import com.robertx22.saveclasses.GearItemData;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GearSaving {

	private static String DataLocation = "GearItemData";
	private static final Class<? extends GearItemData> TheClass = GearItemData.class;

	@SuppressWarnings("unchecked")
	public static <T> T Load(ItemStack stack) {

		if (stack.hasTagCompound() && stack.getTagCompound().hasKey(DataLocation)) {

			T obj = null;

			try {

				NBTTagCompound nbt = null;
				if (stack.getTagCompound() == null) {
					nbt = new NBTTagCompound();
				} else {
					nbt = stack.getTagCompound();
				}

				String str = nbt.getString(DataLocation);
				obj = (T) Gson.instance.fromJson(str, TheClass);

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

			String json = Gson.instance.toJson(obj);
			nbt.setString(DataLocation, json);
			stack.setTagCompound(nbt);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
