package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.network.RarityItemDropPacket;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnItemDroppedSound {
    // todo make interface for this and packets
    // this works server side only.. i'd have to make my own packet and my own sound effects
    @SubscribeEvent
    public static void onItemSpawn(EntityJoinWorldEvent event) {

        if (true) {
            return; //NOT READY YET
        }

        if (event.getWorld().isRemote) {
            return;
        }

        if (event.getEntity() instanceof ItemEntity) {

            ItemEntity en = (ItemEntity) event.getEntity();

            ICommonDataItem data = ICommonDataItem.load(en.getItem());

            if (data != null) {
                if (data.getRarity().getDropSound().hasSound()) {

                    RarityItemDropPacket packet = new RarityItemDropPacket(data.getRarityRank(), en
                            .getPosition());

                    MMORPG.sendToTracking(packet, en.getPosition(), en.world);
                }
            }

        }

    }
}
