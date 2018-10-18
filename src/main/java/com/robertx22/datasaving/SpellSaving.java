package com.robertx22.datasaving;

import com.robertx22.datasaving.bases.Gson;
import com.robertx22.saveclasses.SpellItemData;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpellSaving {
	private static final Class<?> TheClass = SpellItemData.class;
	private static String DataLocation = "Spellata";

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

	public static <T> void Save(ItemStack stack, SpellItemData obj) {
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
