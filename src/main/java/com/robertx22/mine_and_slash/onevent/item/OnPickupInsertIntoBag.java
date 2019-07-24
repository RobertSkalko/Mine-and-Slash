package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.items.bags.BaseBagItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SCollectItemPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

public class OnPickupInsertIntoBag {

    @SubscribeEvent
    public static void onPickupItem(EntityItemPickupEvent event) {

        ItemStack stack = event.getItem().getItem();

        for (int i = 0; i < event.getEntityPlayer().inventory.getSizeInventory(); i++) {
            if (i == event.getEntityPlayer().inventory.currentItem)
                continue; // prevent item deletion

            ItemStack bag = event.getEntityPlayer().inventory.getStackInSlot(i);
            if (!bag.isEmpty() && bag.getItem() instanceof BaseBagItem) {
                BaseBagItem basebag = (BaseBagItem) bag.getItem();
                IItemHandler bagInv = basebag.getInventory(bag, stack);

                if (bagInv == null) {
                    continue;
                }

                stack = loopItems(bagInv, event, stack, true); // once try merge into existing
                stack = loopItems(bagInv, event, stack, false); // second merge even into empty

            }

        }

    }

    private static ItemStack loopItems(IItemHandler bagInv, EntityItemPickupEvent event,
                                       ItemStack stack, boolean searchSame) {

        if (stack.isEmpty()) {
            return stack;
        }

        for (int x = 0; x < bagInv.getSlots(); x++) {

            if (searchSame) {
                if (ItemHandlerHelper.canItemStacksStack(stack, bagInv.getStackInSlot(x)) == false) {
                    continue;
                }
            }

            ItemStack result = bagInv.insertItem(x, stack, false);
            int numPickedUp = stack.getCount() - result.getCount();

            if (numPickedUp > 0) {
                event.getItem().setItem(result);

                event.setCanceled(true);
                if (!event.getItem().isSilent()) {
                    event.getItem().world.playSound(null, event.getEntityPlayer().posX, event
                            .getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((event
                            .getItem().world.rand.nextFloat() - event.getItem().world.rand
                            .nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
                ((ServerPlayerEntity) event.getEntityPlayer()).connection.sendPacket(new SCollectItemPacket(event
                        .getItem()
                        .getEntityId(), event.getEntityPlayer()
                        .getEntityId(), numPickedUp));
                event.getEntityPlayer().openContainer.detectAndSendChanges();

                return result;
            }
        }

        return stack;
    }

}
