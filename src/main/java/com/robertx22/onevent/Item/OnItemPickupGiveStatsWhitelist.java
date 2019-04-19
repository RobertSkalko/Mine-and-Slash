package com.robertx22.onevent.Item;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class OnItemPickupGiveStatsWhitelist {

    @SubscribeEvent
    public static void onItemCraftAddStats(PlayerContainerEvent event) {

	try {
	    if (event.getEntityPlayer().world.isRemote) {
		return;
	    }

	    if (ModConfig.ItemsUnderSystem.CRAFTED_ITEMS_UNDER_SYSTEM == false) {
		return;

	    }

	    UnitData data = null;

	    for (ItemStack stack : event.getEntityPlayer().inventory.mainInventory) {

		if (stack.isEmpty()) {
		    continue;
		}

		GearItemData test = Gear.Load(stack);

		if (test == null) {

		    GearItemSlot type = ModConfig.ItemsUnderSystem.getType(stack.getItem());

		    if (type != null) {

			EntityPlayer player = event.getEntityPlayer();

			if (player.hasCapability(EntityData.Data, null)) {

			    if (data == null) {
				data = Load.Unit(player);
			    }

			    GearBlueprint schema = new GearBlueprint(data.getLevel());
			    schema.SetSpecificType(type.GUID());

			    GearItemData gear = GearGen.CreateData(schema);
			    gear.isNotFromMyMod = true;

			    Gear.Save(stack, gear);

			    event.getEntityPlayer().inventory.markDirty();

			}
		    }
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
