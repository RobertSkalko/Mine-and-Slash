package com.robertx22.constants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

import com.robertx22.capability.EntityData;

public class Mob {

	public static final String[] rarityNames = { "Magic", "Rare", "Epic", "Legendary", "Mythical" };

	public static final int[] rarityDMGMulti = { 1, 2, 4, 8, 10 };

	public static final int[] rarityHPMulti = { 2, 3, 6, 15, 30 };
	public static final int[] rarityXPMulti = { 1, 2, 4, 8, 20 };

	public static int getRandomLevel(EntityPlayer player) {

		Random ran = new Random();

		NBTTagCompound playerNBT = player.getCapability(EntityData.Data, null).getNBT();

		if (playerNBT == null) {
			return 0;
		}

		int lvl = playerNBT.getInteger(Tags.LEVEL) + 1;

		lvl = lvl / 4 + lvl / 2 + (ran.nextInt(lvl) + 2) / 2;

		if (lvl < 1) {
			lvl = 1;
		}

		return lvl;

	}

}
