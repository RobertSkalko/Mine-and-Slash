package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.mine_and_slash.items.misc.ItemCapacitor;
import com.robertx22.mine_and_slash.items.misc.ItemIncreaseRarityNearestEntity;
import com.robertx22.mine_and_slash.items.misc.ItemNewbieGearBag;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        putInLists();
        registerSingles(event);
        registerLists(event);

    }

    private static void putInLists() {

    }

    private static void registerLists(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        ItemCapacitor.ITEMS.values()
            .forEach((x) -> r.register(x));

        for (IUnique uniq : SlashRegistry.UniqueGears()
            .getSerializable()) {
            r.register(uniq.getItemForRegistration());
        }

    }

    private static List<Item> list = new ArrayList<>();

    public static void shcheduleToRegister(Item item) {
        list.add(item);
    }

    private static void registerSingles(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        list.add(new ItemCurrencyBag().setRegistryName(ItemCurrencyBag.ID));

        list.add(new ItemIncreaseRarityNearestEntity());
        list.add(new ItemNewbieGearBag());

        list.add(SimpleMatItem.CRYSTALLIZED_ESSENCE);
        list.add(SimpleMatItem.INFUSED_IRON);
        list.add(SimpleMatItem.MYTHIC_ESSENCE);
        list.add(SimpleMatItem.GOLDEN_ORB);

        for (Item item : list) {
            if (item instanceof IGenerated) {
                IGenerated gen = (IGenerated) item;
                gen.generateAllPossibleStatVariations()
                    .forEach(x -> r.register((Item) x));

            } else {
                r.register(item);
            }

        }

    }

}


