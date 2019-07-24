package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.PlayerOncePerMapData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class PlayerOncePerMap {

    private static final String LOC = "mmorpg:PLAYER_ONCE_PER_MAP_DATA";

    public static PlayerOncePerMapData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(PlayerOncePerMapData.class, new PlayerOncePerMapData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, PlayerOncePerMapData gear) {

        if (nbt == null) {
            return;
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
