
package com.robertx22.uncommon.capability;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.saveclasses.DimensionData;
import com.robertx22.saveclasses.MapDataList;

import info.loenwind.autosave.Reader;
import info.loenwind.autosave.Writer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.DimensionType;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.DimensionManager;

public class MapDatas extends WorldSavedData {

	public static List<Integer> dimensionIds = new ArrayList<Integer>();

	public MapDatas(String name) {
		super(name);

		this.LOCATION = getLoc();
		this.setDirty(true);
	}

	public static String getLoc() {
		return DimensionManager.getCurrentSaveRootDirectory().getName();
	}

	String LOCATION;

	MapDataList list = new MapDataList();

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		if (list != null) {
			NBTTagCompound dimnbt = new NBTTagCompound();
			Writer.write(dimnbt, list);
			nbt.setTag(LOCATION, dimnbt);
		}

		return nbt;
	}

	@Override
	public void readFromNBT(NBTTagCompound value) {

		NBTTagCompound dimnbt = (NBTTagCompound) value.getTag(LOCATION);
		if (dimnbt != null) {
			Reader.read(dimnbt, list);
		}
	}

	public void register(DimensionData data) {
		reg(data);
		list.dimDatas.put(data.ID, data);
		this.setDirty(true);

	}

	public void delete(int id) {
		this.list.dimDatas.remove(id);
		this.setDirty(true);

		for (int i = 0; i < dimensionIds.size(); i++) {
			if (dimensionIds.get(i) == id) {
				dimensionIds.remove(i);
				break;
			}
		}

		if (!DimensionManager.isDimensionRegistered(id)) {
			DimensionManager.unregisterDimension(id);
		}
	}

	private void reg(DimensionData data) {
		DimensionType type = data.getDimensionType();

		this.dimensionIds.add(data.ID);

		if (type != null) {

			if (!DimensionManager.isDimensionRegistered(data.ID)) {

				DimensionManager.registerDimension(data.ID, type);
			}
		} else {

			System.out.println("Dimension Error");
		}
	}

	public void registerDimensions() {

		this.dimensionIds.clear();

		for (DimensionData data : list.dimDatas.values()) {
			reg(data);
			this.dimensionIds.add(data.ID);

		}
	}

	public static void unregisterDimensions() {
		for (int id : dimensionIds) {
			if (DimensionManager.isDimensionRegistered(id)) {
				DimensionManager.unregisterDimension(id);
			}

		}
	}

}
