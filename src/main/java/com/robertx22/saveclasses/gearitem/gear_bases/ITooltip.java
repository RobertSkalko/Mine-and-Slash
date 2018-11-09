package com.robertx22.saveclasses.gearitem.gear_bases;

import com.robertx22.saveclasses.Unit;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public interface ITooltip {

	public abstract void BuildTooltip(ItemTooltipEvent event, Unit unit);

}
