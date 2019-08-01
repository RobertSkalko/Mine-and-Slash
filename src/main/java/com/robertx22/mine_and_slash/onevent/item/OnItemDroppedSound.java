package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnItemDroppedSound {
    // todo make interface for this and packets
    // this works server side only.. i'd have to make my own packet and my own sound effects
    @SubscribeEvent
    public static void onItemSpawn(EntityJoinWorldEvent event) {

        if (true)
            return;

        if (event.getWorld().isRemote) {
            return;
        }
        if (event.getEntity() instanceof ItemEntity) {

            ItemEntity en = (ItemEntity) event.getEntity();

            ICommonDataItem data = ICommonDataItem.load(en.getItem());

            if (data != null) {
                if (data.getRarityRank() == IRarity.Mythic) {
                    en.playSound(SoundEvents.UI_LOOM_TAKE_RESULT, 1, 1);
                }
                if (data.getRarityRank() == IRarity.Unique) {
                    en.playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                }
            }

        }

    }
}
