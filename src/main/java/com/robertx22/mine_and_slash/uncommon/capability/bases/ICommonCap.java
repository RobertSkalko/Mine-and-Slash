package com.robertx22.mine_and_slash.uncommon.capability.bases;

import net.minecraft.nbt.CompoundNBT;

public interface ICommonCap {
    CompoundNBT saveToNBT();

    void loadFromNBT(CompoundNBT value);

}
