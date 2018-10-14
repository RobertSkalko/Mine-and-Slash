package com.robertx22.onevent.Item;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saving.Saving;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnTooltip {

	ItemStack item;
	NBTTagCompound nbt;
	NBTTagCompound statsNBT;
	NBTTagCompound enchantsNBT;
	NBTTagCompound socketNBT;

	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) throws Exception {

		if (event.getEntityPlayer() != null && event.getEntityPlayer().world != null
				&& !event.getEntityPlayer().world.isRemote) {
			return;
		}

		item = event.getItemStack();

		if (item == null) {
			return;
		}
		if (!item.hasTagCompound()) {
			return;
		}

		GearItemData data = Saving.Load(item, GearItemData.class);

		if (data != null) {

			data.BuildTooltip(event);

		}

	}

}
