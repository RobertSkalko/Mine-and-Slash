package com.robertx22.mine_and_slash.uncommon.capability.bases;

import com.robertx22.mine_and_slash.packets.sync_cap.MobCaps;
import net.minecraft.nbt.CompoundNBT;

public interface ICommonMobCap extends ICommonCap {
    CompoundNBT getNBT();

    void setNBT(CompoundNBT value);

    MobCaps getCapType();

}
