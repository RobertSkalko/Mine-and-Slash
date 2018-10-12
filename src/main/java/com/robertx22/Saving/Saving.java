package com.robertx22.saving;

import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.nbt.NBTTagCompound;

public class Saving {

	private static Gson gson = new Gson();	
	
	
	
	public static NBTTagCompound Save(NBTTagCompound nbt, Object thing) {
		
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}

		nbt.setString("Data", gson.toJson(thing));

		return nbt;

	}

	public static <T> T Load(NBTTagCompound nbt, Class type) {

		if (nbt == null) return null;
		
		String str = nbt.getString("Data");
		
		if (str == null)return null;
		
		Object object = gson.fromJson(str, type);
		
		if (object == null) return null;
		
		return (T) object;
	}
}
