package com.robertx22.saving;

import com.google.gson.Gson;
import com.robertx22.capability.EntityData;
import com.robertx22.saveclasses.Unit;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

	public static <T> T Load(EntityLivingBase player, Class<?> type) {

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

		// System.out.println(str);

		Object object = gson.fromJson(str, type);

		if (object == null)
			return null;

		return (T) object;
	}

	public static <T> void SaveToItem(ItemStack stack, Object obj) {

		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		stack.getTagCompound().setString(DataLocation, gson.toJson(obj));

	}

	public static <T> void SaveToEntity(EntityLivingBase entity, Object obj) throws Exception {

		if (entity.hasCapability(EntityData.Data, null)) {

			entity.getCapability(EntityData.Data, null).getNBT().setString(DataLocation, gson.toJson(obj));
		} else {
			throw new Exception("Entity has no EntityData capability!");
		}

	}
}
