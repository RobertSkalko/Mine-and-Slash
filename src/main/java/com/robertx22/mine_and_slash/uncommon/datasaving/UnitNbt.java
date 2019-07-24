package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import net.minecraft.nbt.CompoundNBT;

public class UnitNbt {
    private static final String LOC = "unit_object";

    public static Unit Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(Unit.class, new Unit(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, Unit gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
