package com.robertx22.datasaving.bases;

import com.robertx22.capability.ICommonData;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class Saving {

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

	public static <T, Interface extends ICommonData> void Save(ICapabilitySerializable<NBTTagCompound> entity,
			Object obj, Capability<Interface> capability, String path) {

		if (entity != null && entity.hasCapability(capability, null)) {

			try {

				Interface datacapa = (Interface) entity.getCapability(capability, null);

				datacapa.getNBT().setString(path, Gson.instance.toJson(obj));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("ERROR can't save capability!");
		}
	}

}
