package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.StructuresData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class Structures {

    private static final String LOC = "STRUCTURES_DATA";

    public static StructuresData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(StructuresData.class, new StructuresData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, StructuresData gear) {

        if (nbt == null) {
            nbt = new CompoundNBT();
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
