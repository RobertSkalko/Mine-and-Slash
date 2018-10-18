package com.robertx22.capability;

import net.minecraft.nbt.NBTTagCompound;

public interface ICommonData {

	NBTTagCompound getNBT();

	void setNBT(NBTTagCompound value);

}
