package com.robertx22.onevent.Gearupgrade;

import com.robertx22.constants.Tags;
import com.robertx22.customitems.MyItems;
import com.robertx22.utilityclasses.ItemUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnAnvilUpgrade {

	@SubscribeEvent
	public void OnAnvilDoUpgrade(AnvilUpdateEvent event) {

		Item mat = event.getRight().getItem();
		ItemStack item = event.getLeft().copy();

		if (ItemUtils.isGear(event.getLeft())) {

			int matCost = 10;

			NBTTagCompound nbt = item.getTagCompound();

			int upgradeNumber = nbt.getInteger(Tags.UPGRADE_NUMBER);

			if (upgradeNumber > 9) {
				return;
			}

			if (event.getRight().getCount() < 10) {
				return;
			}

			if (mat.equals(MyItems.magic_powder)) {

				if (upgradeNumber == 0 || upgradeNumber == 1) {

					event.setOutput(ItemUtils.upgradeItem(item));
				}

			}
			if (mat.equals(MyItems.rare_powder)) {

				if (upgradeNumber == 2 || upgradeNumber == 3) {

					event.setOutput(ItemUtils.upgradeItem(item));
				}

			}
			if (mat.equals(MyItems.epic_powder)) {

				if (upgradeNumber == 4 || upgradeNumber == 5) {

					event.setOutput(ItemUtils.upgradeItem(item));
				}

			}
			if (mat.equals(MyItems.legendary_powder)) {

				if (upgradeNumber == 6 || upgradeNumber == 7) {

					event.setOutput(ItemUtils.upgradeItem(item));
				}

			}
			if (mat.equals(MyItems.mythical_powder)) {

				if (upgradeNumber == 8 || upgradeNumber == 9) {

					event.setOutput(ItemUtils.upgradeItem(item));
				}

			}

			event.setCost(1);
			event.setMaterialCost(matCost);

		}

	}

}