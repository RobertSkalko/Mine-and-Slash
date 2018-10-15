package com.robertx22.utilityclasses;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saving.Saving;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OnItemCreatedUtils {

	public static void TryReroll(ItemStack stack, World worldIn) {
		if (!worldIn.isRemote) {
			GearItemData data = Saving.Load(stack, GearItemData.class);
			if (data != null) {
				data.TryRerollComponents();
				Saving.Save(stack, data);

			}
		}
	}
}
