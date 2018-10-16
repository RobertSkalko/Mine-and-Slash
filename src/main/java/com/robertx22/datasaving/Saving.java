package com.robertx22.datasaving;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertx22.capability.EntityData;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Saving {

	private static final Class<? extends Unit> UnitClass = Unit.class;
	private static final Class<? extends GearItemData> GearClass = GearItemData.class;

	private static String DataLocation = "PathOfMinecraftData";
	private static String StackData = "ItemStackData";

	private static GsonBuilder gsonBilder = new GsonBuilder().registerTypeAdapter(Stat.class,
			new InterfaceAdapter<Stat>());

	private static Gson gson = gsonBilder.create();

	@SuppressWarnings("unchecked")
	public static <T> T Load(Entity entity) {

		if (entity.hasCapability(EntityData.Data, null)) {
			try {
				Unit unit = gson.fromJson(entity.getCapability(EntityData.Data, null).getNBT().getString(DataLocation),
						UnitClass);
				if (entity instanceof EntityLivingBase) {
					unit.entity = (EntityLivingBase) entity;
				}

				return (T) unit;
			} catch (Exception e) {

				e.printStackTrace();
			}
		} else {

			System.out.println("ERROR can't load capability!");

		}
		return null;

	}

	public static <T> void Save(Entity entity, Object obj) {

		if (entity.hasCapability(EntityData.Data, null)) {

			try {
				entity.getCapability(EntityData.Data, null).getNBT().setString(DataLocation, gson.toJson(obj));
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
