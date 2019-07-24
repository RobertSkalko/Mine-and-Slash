package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.saveclasses.PlayersCapBackup;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class PlayersCapBackupSaving {

    private static final String LOC = "mmorpg:players_cap_backup";

    public static PlayersCapBackup Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(PlayersCapBackup.class, new PlayersCapBackup(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, PlayersCapBackup gear) {

        if (nbt == null) {
            nbt = new CompoundNBT();
        }

        if (gear != null) {
            LoadSave.Save(gear, nbt, LOC);
        }

    }
}
