package com.robertx22.uncommon.capability.bases;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface ICommonCapability {

	void syncToClient(EntityPlayer player);

	NBTTagCompound getNBT();

	void setNBT(NBTTagCompound value);
}
