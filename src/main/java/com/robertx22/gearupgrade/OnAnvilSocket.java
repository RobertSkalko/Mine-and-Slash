package com.robertx22.gearupgrade;

import com.robertx22.constants.Tag;
import com.robertx22.utilityclasses.ItemUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnAnvilSocket {

    @SubscribeEvent
    public void OnAnvilDoSocket(AnvilUpdateEvent event) {

        Item mat = event.getRight().getItem();
        ItemStack item = event.getLeft().copy();

        if (ItemUtils.isGear(event.getLeft())) {

            if (ItemUtils.isSocket(mat)) {

                if (item.getTagCompound().getString(Tag.GEAR_TYPE).equals(event.getRight().getTagCompound().getString
                        (Tag.GEAR_TYPE))) {

                    event.setCost(1);
                    event.setMaterialCost(1);

                    item = transferSocketToItem(item, event.getRight());

                    event.setOutput(item);
                }
                else {
                    event.setCost(0);
                }

            }

        }

    }

    ItemStack transferSocketToItem(ItemStack item, ItemStack socket) {

        item.getTagCompound().setTag(Tag.SOCKETS, socket.getTagCompound());

        return item;
    }

}

