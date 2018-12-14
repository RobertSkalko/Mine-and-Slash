package com.robertx22.onevent.Item;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class OnItemCrafted {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onItemCraftAddStats(PlayerEvent.ItemCraftedEvent event) {

	try {
	    if (event.player.world.isRemote) {
		return;
	    }

	    if (ModConfig.Server.CRAFTED_ITEMS_UNDER_SYSTEM == false) {
		return;
	    }

	    ItemStack stack = event.crafting;

	    GearItemSlot type = ModConfig.ItemsUnderSystem.getType(stack.getItem());

	    if (type != null) {

		EntityPlayer player = event.player;

		if (player.hasCapability(EntityData.Data, null)) {

		    UnitData data = Load.Unit(player);

		    GearBlueprint schema = new GearBlueprint(data.getLevel());
		    schema.SetSpecificType(type.GUID());

		    GearItemData gear = GearGen.CreateData(schema);

		    Gear.Save(stack, gear);
		}

	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
}
