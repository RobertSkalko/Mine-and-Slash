package com.robertx22.uncommon.datasaving.bases;

import javax.annotation.Nonnull;

import com.robertx22.uncommon.capability.ICommonData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class Saving {

	public static String ToString(Object obj) {
		return Gson.instance.toJson(obj);

	}

	@Nonnull
	@SuppressWarnings("unchecked")
	public static <T, Interface extends ICommonData> T Load(String str, Class<?> theclass) {

		try {

			return (T) Gson.instance.fromJson(str, theclass);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;

	}

	@Nonnull
	@SuppressWarnings("unchecked")
	public static <T, Interface extends ICommonData> T Load(ICapabilitySerializable<NBTTagCompound> entity,
			Capability<Interface> capability, String path, Class<?> theclass) {

		if (entity != null && entity.hasCapability(capability, null)) {
			try {

				return (T) Gson.instance.fromJson(
						((Interface) entity.getCapability(capability, null)).getNBT().getString(path), theclass);
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {

			System.out.println("ERROR can't load capability!");

		}
		return null;

	}

	@Nonnull
	public static <T, Interface extends ICommonData> void Save(ICapabilitySerializable<NBTTagCompound> entity,
			Object obj, Capability<Interface> capability, String path) {

		if (entity != null && entity.hasCapability(capability, null) && obj != null) {

			try {

				Interface datacapa = (Interface) entity.getCapability(capability, null);

				String json = Gson.instance.toJson(obj);

				datacapa.getNBT().setString(path, json);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("ERROR can't save capability!");
		}
	}

}
