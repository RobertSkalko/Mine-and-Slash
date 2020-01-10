package com.robertx22.mine_and_slash.uncommon.capability.bases;

import net.minecraft.nbt.CompoundNBT;

public interface INeededForClient {
    CompoundNBT getClientNBT();

    void setClientNBT(CompoundNBT value);
}
