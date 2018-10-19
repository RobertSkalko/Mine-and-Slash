package com.robertx22.uncommon.utilityclasses;

import com.robertx22.saveclasses.gearitem.GearItemData;
import com.robertx22.uncommon.datasaving.GearSaving;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OnItemCreatedUtils {

	public static void TryReroll(ItemStack stack, World worldIn) {
		if (!worldIn.isRemote) {
			GearItemData data = GearSaving.Load(stack);
			if (data != null) {
				data.TryRerollComponents();
				GearSaving.Save(stack, data);

			}
		}
	}
}
