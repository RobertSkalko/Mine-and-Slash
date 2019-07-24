package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.a_libraries.curios.CurioSlots;
import com.robertx22.mine_and_slash.a_libraries.curios.MyCurioUtils;
import com.robertx22.mine_and_slash.items.bags.AutoSalvageBag;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ISalvagable;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnPickUpSalvage {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPickupItem(EntityItemPickupEvent event) {

        if (event.getEntityPlayer() != null) {

            PlayerEntity player = event.getEntityPlayer();

            if (!player.world.isRemote) {

                ItemStack bag = MyCurioUtils.getSlot(player, CurioSlots.SALVAGE_BAG);

                if (bag.getItem() instanceof AutoSalvageBag) {

                    ItemStack stack = event.getItem().getItem();
                    ISalvagable sal = AutoSalvageBag.getSalvagable(stack);

                    if (sal != null) {
                        AutoSalvageBag salvageBag = (AutoSalvageBag) bag.getItem();

                        if (salvageBag.shouldSalvageItem(sal, bag.getTag())) {

                            ItemStack result = salvageBag.getSalvageResultForItem(sal);
                            if (result.isEmpty() == false) {
                                stack.setCount(0);

                                ItemEntity enitem = new ItemEntity(player.world, player.posX, player.posY, player.posZ, result);
                                enitem.setNoPickupDelay();
                                WorldUtils.spawnEntity(player.world, enitem);
                            } else {
                                // this shouldnt happen
                            }
                        }
                    }

                }

            }
        }
    }

}