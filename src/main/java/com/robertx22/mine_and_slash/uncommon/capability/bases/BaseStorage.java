package com.robertx22.mine_and_slash.uncommon.capability.bases;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class BaseStorage<TYPE extends ICommonCapability> implements Capability.IStorage<TYPE> {

    @Override
    public INBT writeNBT(Capability<TYPE> capability, TYPE instance,
                         Direction side) {

        return instance.getNBT();
    }

    @Override
    public void readNBT(Capability<TYPE> capability, TYPE instance, Direction side,
                        INBT nbt) {

        instance.setNBT((CompoundNBT) nbt);

    }

}