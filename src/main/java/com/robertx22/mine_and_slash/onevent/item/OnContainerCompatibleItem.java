package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.new_content.auto_comp.PowerLevel;
import com.robertx22.mine_and_slash.registry.FilterListWrap;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnContainerCompatibleItem {

    @SubscribeEvent
    public static void onContainerCompatibleItem(PlayerContainerEvent event) {

        try {

            if (ModConfig.INSTANCE.Server.USE_COMPATIBILITY_ITEMS.get() == false) {
                return;
            }
            if (event.getPlayer().world.isRemote) {
                return;
            }

            UnitData data = null;

            for (ItemStack stack : event.getPlayer().inventory.mainInventory) {

                if (stack.isEmpty()) {
                    continue;
                }

                // fast check for every item
                if (Gear.has(stack) == false) {

                    String reg = stack.getItem()
                        .getRegistryName()
                        .toString();

                    FilterListWrap<CompatibleItem> matchingItems = SlashRegistry.CompatibleItems()
                        .getFilterWrapped(x -> x.item_id.equals(reg.toString()) && !x.only_add_stats_if_loot_drop);

                    CompatibleItem config = null;

                    if (!matchingItems.list.isEmpty()) {
                        config = matchingItems.random();
                    } else {

                        if (ModConfig.INSTANCE.autoCompatibleItems.ENABLE_AUTOMATIC_COMPATIBLE_ITEMS.get()) {

                            final ItemStack finalStack = stack;

                            final Item item = finalStack.getItem();

                            FilterListWrap<GearItemSlot> wrapped = SlashRegistry.GearTypes()
                                .getFilterWrapped(x -> GearItemSlot.isGearOfThisType(x, item));

                            if (!wrapped.list.isEmpty()) {

                                GearItemSlot slot = wrapped.random();

                                PowerLevel.Types type = PowerLevel.getPowerClassification(item);

                                config = type.getConfig()
                                    .getAutoCompatibleItem(item, slot);

                            }

                        }
                    }

                    if (config != null) {
                        if (data == null) {
                            data = Load.Unit(event.getPlayer());
                        }

                        // slow check to make absolutely sure it doesnt have stats
                        GearItemData gear = Gear.Load(stack);
                        if (gear == null) {
                            stack = config.create(stack, data.getLevel());
                            event.getPlayer().inventory.markDirty();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
