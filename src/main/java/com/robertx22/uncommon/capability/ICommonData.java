package com.robertx22.uncommon.capability;

import net.minecraft.nbt.NBTTagCompound;

public interface ICommonData {

	NBTTagCompound getNBT();

	void setNBT(NBTTagCompound value);

}
