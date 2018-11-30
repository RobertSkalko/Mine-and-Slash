package com.robertx22.onevent.Item;

import com.robertx22.customitems.gearitems.bases.IGearItem;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class OnTooltip {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {

	if (event.getEntityPlayer() == null || event.getEntityPlayer().world == null
		|| !event.getEntityPlayer().world.isRemote) {
	    return;
	}

	ItemStack stack;

	stack = event.getItemStack();

	if (stack == null) {
	    return;
	}
	if (!stack.hasTagCompound()) {
	    return;
	}

	if (GuiScreen.isCtrlKeyDown() == false) {
	    if (stack.getItem() instanceof IGearItem) {

		Unit unit = UnitSaving.Load(event.getEntityPlayer());
		GearItemData gear = Gear.Load(stack);

		if (unit != null && gear != null) {

		    gear.BuildTooltip(event, unit, event.getEntityPlayer().getCapability(EntityData.Data, null));

		    if (GuiScreen.isShiftKeyDown() == false) {

			event.getToolTip().add("Press shift for more info");
		    }
		}
	    }
	}
    }

}
