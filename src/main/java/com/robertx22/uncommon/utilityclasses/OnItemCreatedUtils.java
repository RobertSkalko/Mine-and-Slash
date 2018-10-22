package com.robertx22.uncommon.utilityclasses;

import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.uncommon.datasaving.GearSaving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OnItemCreatedUtils {

	public static void TryReroll(EntityPlayer player, ItemStack stack, World worldIn) {
		if (!worldIn.isRemote) {
			GearItemData data = GearSaving.Load(stack);
			if (data != null) {
				data.TryRerollComponents(player);
				GearSaving.Save(stack, data);

			}
		}
	}
}
