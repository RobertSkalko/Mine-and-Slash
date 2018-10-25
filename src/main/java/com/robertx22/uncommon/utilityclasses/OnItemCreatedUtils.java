package com.robertx22.uncommon.utilityclasses;

import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.uncommon.datasaving.GearSaving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OnItemCreatedUtils {

	public static void TryReroll(EntityPlayer player, ItemStack stack, World worldIn) {
		if (!worldIn.isRemote) {
			GearItemData data = GearSaving.Load(stack);
			if (data != null) {
				data.TryRerollComponents(player);

				if (data.secondaryStats.AddStat && !data.secondaryStats.AddedStat) {
					data.secondaryStats.AddStat(data);

					SoundUtils.playSoundAtPlayer(player, SoundEvents.BLOCK_ANVIL_USE, 1, 1);
				}

				GearSaving.Save(stack, data);

			}

		}
	}
}
