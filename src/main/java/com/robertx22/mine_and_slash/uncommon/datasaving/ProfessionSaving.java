package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.professions.ProfessionListData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class ProfessionSaving {
    private static final String LOC = "PROFESSIONS_DATA";

    public static ProfessionListData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(ProfessionListData.class, new ProfessionListData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, ProfessionListData data) {

        if (nbt == null) {
            return;
        }

        if (data != null) {
            LoadSave.Save(data, nbt, LOC);
        }

    }

}
