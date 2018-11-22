package com.robertx22.dimensions.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TilePortalBlock extends TileEntity {

	public int id;

	public TilePortalBlock(int id) {
		this.id = id;
	}

	public TilePortalBlock() {

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		id = nbt.getInteger("ID");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt); // The super call is required to save and load the tiles location

		nbt.setInteger("ID", id);

		return nbt;
	}

}
