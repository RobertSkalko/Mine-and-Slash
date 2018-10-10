package com.robertx22.saving;

import com.google.gson.Gson;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;

public class Saving {

	public static NBTTagCompound Save(NBTTagCompound nbt, Object thing) {

		if (nbt == null) {
			nbt = new NBTTagCompound();
		}

		nbt.setString("Data", new Gson().toJson(thing));

		return nbt;

	}

	public static <T> T Load(NBTTagCompound nbt, Class type) {

		return (T) new Gson().fromJson(nbt.getString("Data"), type);
	}
}
