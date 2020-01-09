package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.items.bags.BaseBagItem;
import com.robertx22.mine_and_slash.items.bags.BaseInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SCollectItemPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;

public class OnPickupInsertIntoBag {

    @SubscribeEvent
    public static void onPickupItem(EntityItemPickupEvent event) {

        ItemStack stack = event.getItem().getItem();

        for (int i = 0; i < event.getPlayer().inventory.getSizeInventory(); i++) {
            if (i == event.getPlayer().inventory.currentItem)
                continue; // prevent item deletion

            ItemStack bag = event.getPlayer().inventory.getStackInSlot(i);
            if (!bag.isEmpty() && bag.getItem() instanceof BaseBagItem) {
                BaseBagItem basebag = (BaseBagItem) bag.getItem();
                BaseInventory bagInv = basebag.getInventory(bag, stack);

                if (bagInv == null) {
                    continue;
                }

                InvWrapper handler = new InvWrapper(bagInv);

                stack = loopItems(bagInv, handler, event, stack, true); // once try merge into existing
                stack = loopItems(bagInv, handler, event, stack, false); // second merge even into empty

            }

        }

    }

    private static ItemStack loopItems(BaseInventory baseInv, IItemHandler handler,
                                       EntityItemPickupEvent event, ItemStack stack,
                                       boolean searchSame) {

        if (stack.isEmpty()) {
            return stack;
        }

        for (int x = 0; x < handler.getSlots(); x++) {

            if (searchSame) {
                if (ItemHandlerHelper.canItemStacksStack(stack, handler.getStackInSlot(x)) == false) {
                    continue;
                }
            }

            ItemStack result = handler.insertItem(x, stack, false);
            int numPickedUp = stack.getCount() - result.getCount();

            if (numPickedUp > 0) {

                baseInv.writeItemStack();

                event.getItem().setItem(result);

                event.setCanceled(true);
                if (!event.getItem().isSilent()) {
                    event.getItem().world.playSound(null, event.getPlayer().posX, event.getPlayer().posY, event
                            .getPlayer().posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((event
                            .getItem().world.rand.nextFloat() - event.getItem().world.rand
                            .nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
                ((ServerPlayerEntity) event.getPlayer()).connection.sendPacket(new SCollectItemPacket(event
                        .getItem()
                        .getEntityId(), event.getPlayer().getEntityId(), numPickedUp));
                event.getPlayer().openContainer.detectAndSendChanges();

                return result;
            }
        }

        return stack;
    }

}
