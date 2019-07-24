package com.robertx22.mine_and_slash.uncommon.capability.bases;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public abstract class BaseProvider<TYPE> implements ICapabilitySerializable<CompoundNBT> {

    public abstract TYPE defaultImpl();

    public abstract Capability<TYPE> dataInstance();

    TYPE impl = defaultImpl();
    private final LazyOptional<TYPE> cap = LazyOptional.of(() -> impl);

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) dataInstance().getStorage()
                .writeNBT(dataInstance(), impl, null);

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        dataInstance().getStorage().readNBT(dataInstance(), impl, null, nbt);

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == dataInstance()) {
            return this.cap.cast();
        }
        return LazyOptional.empty();
    }
}