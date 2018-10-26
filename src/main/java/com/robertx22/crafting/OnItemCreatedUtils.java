package com.robertx22.crafting;

import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.uncommon.datasaving.GearSaving;
import com.robertx22.uncommon.utilityclasses.SoundUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OnItemCreatedUtils {

	public static void TryReroll(EntityPlayer player, ItemStack stack, World worldIn) {
		if (!worldIn.isRemote) {

			GearItemData data = GearSaving.Load(stack);

			if (data.ShowInfoAfterCrafting(stack)) {
				SoundUtils.playSoundAtPlayer(player, SoundEvents.BLOCK_ANVIL_USE, 1, 1);
			}

		}
	}
}
