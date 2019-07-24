package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.CustomStatsData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class CustomStats {

    public static final String LOC = "mmorpg:custom_stats_data";

    public static CustomStatsData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(CustomStatsData.class, new CustomStatsData(), nbt, LOC);

    }

    public static CompoundNBT Save(CompoundNBT nbt, CustomStatsData gear) {

        if (nbt == null) {
            return new CompoundNBT();
        }

        if (gear != null) {
            return LoadSave.Save(gear, nbt, LOC);
        }

        return new CompoundNBT();
    }
}
