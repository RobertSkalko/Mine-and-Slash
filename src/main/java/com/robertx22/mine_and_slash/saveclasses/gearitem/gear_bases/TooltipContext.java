package com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipContext {

    public TooltipContext(ItemStack stack, ItemTooltipEvent event, UnitData data) {
        this.stack = stack;
        this.event = event;
        this.data = data;
    }

    public ItemStack stack;
    public ItemTooltipEvent event;
    public UnitData data;

}
