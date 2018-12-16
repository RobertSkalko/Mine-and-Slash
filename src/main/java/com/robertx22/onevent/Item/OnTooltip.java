package com.robertx22.onevent.Item;

import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.Map;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
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

	UnitData unitdata = Load.Unit(event.getEntityPlayer());
	Unit unit = null;
	if (unitdata != null) {
	    unit = unitdata.getUnit();
	}
	if (GuiScreen.isCtrlKeyDown() == false) {

	    GearItemData gear = Gear.Load(stack);

	    if (unit != null && gear != null) {

		gear.BuildTooltip(stack, event, unit, event.getEntityPlayer().getCapability(EntityData.Data, null));

		if (GuiScreen.isShiftKeyDown() == false) {

		    event.getToolTip().add(CLOC.tooltip("press_shift_more_info"));
		}

	    }
	}

	if (unitdata != null) {
	    MapItemData map = Map.Load(stack);
	    if (map != null) {
		event.getToolTip().add("");
		event.getToolTip().add(TextFormatting.GOLD + CLOC.tooltip("affix_rarity_lootbonus") + ": "
			+ unitdata.getLootBonusPerAffixKills(map) + "%");

	    }
	}

    }

}
