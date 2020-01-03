package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.blocks.map_device.MapDeviceData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class MapDeviceSaving {

    private static final String LOC = "mmorpg:map_device_data";

    public static MapDeviceData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(MapDeviceData.class, new MapDeviceData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, MapDeviceData data) {

        if (nbt == null) {
            return;
        }

        if (data != null) {
            LoadSave.Save(data, nbt, LOC);
        }

    }
}
