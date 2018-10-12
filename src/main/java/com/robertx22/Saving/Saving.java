package com.robertx22.saving;

import com.google.gson.Gson;
import com.robertx22.capability.EntityData;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class Saving {

	private static String DataLocation = "PathOfMinecraftData";
	private static Gson gson = new Gson();

	public static NBTTagCompound Save(NBTTagCompound nbt, Object thing) {

		if (nbt == null) {
			nbt = new NBTTagCompound();
		}

		nbt.setString(DataLocation, gson.toJson(thing));

		return nbt;

	}

	public static <T> T Load(EntityPlayer player, Class<?> type) {

		return Load(player.getCapability(EntityData.Data, null).getNBT(), Unit.class);

	}

	public static <T> T Load(EntityLiving entity, Class<?> type) {

		return Load(entity.getCapability(EntityData.Data, null).getNBT(), Unit.class);

	}

	public static <T> T Load(NBTTagCompound nbt, Class<?> type) {

		if (nbt == null)
			return null;

		String str = nbt.getString(DataLocation);

		if (str == null)
			return null;

		System.out.println(str);

		Object object = gson.fromJson(str, type);

		if (object == null)
			return null;

		return (T) object;
	}
}
