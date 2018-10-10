package com.robertx22.item;

import com.robertx22.Stats.Stat;
import com.robertx22.Stats.Stats;
import com.robertx22.constants.Gear;
import com.robertx22.constants.Tags;
import com.robertx22.utilityclasses.ItemUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class OnTooltip {

    ItemStack item;
    NBTTagCompound nbt;
    NBTTagCompound statsNBT;
    NBTTagCompound enchantsNBT;
    NBTTagCompound socketNBT;
  
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent event) {

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

        if (ItemUtils.isGear(item)) {

        
         
         //   event.getToolTip().add("Stats:");

         
        }      

    }


}
