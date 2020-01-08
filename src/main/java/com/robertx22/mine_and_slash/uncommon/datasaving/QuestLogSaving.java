package com.robertx22.mine_and_slash.uncommon.datasaving;

import com.robertx22.mine_and_slash.quests.data.QuestLogData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;

public class QuestLogSaving {
    private static final String LOC = "QUEST_LOG";

    public static QuestLogData Load(CompoundNBT nbt) {

        if (nbt == null) {
            return null;
        }

        return LoadSave.Load(QuestLogData.class, new QuestLogData(), nbt, LOC);

    }

    public static void Save(CompoundNBT nbt, QuestLogData data) {

        if (nbt == null) {
            return;
        }

        if (data != null) {
            LoadSave.Save(data, nbt, LOC);
        }

    }

}

