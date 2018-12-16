package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData.UnitData;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public interface ITooltip {

    public abstract void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit, UnitData data);

}
