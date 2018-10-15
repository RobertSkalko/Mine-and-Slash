package com.robertx22.onevent.Item;

import com.robertx22.datasaving.Saving;
import com.robertx22.saveclasses.GearItemData;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnTooltip {

	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent event) throws Exception {

		if (event.getEntityPlayer() == null || event.getEntityPlayer().world == null
				|| !event.getEntityPlayer().world.isRemote) {
			return;
		}

		ItemStack item;

		item = event.getItemStack();

		if (item == null) {
			return;
		}
		if (!item.hasTagCompound()) {
			return;
		}

		GearItemData data = Saving.Load(item);

		if (data != null) {

			data.BuildTooltip(event);

		}

	}

}
