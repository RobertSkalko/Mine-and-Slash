package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.loot_crates.loot_crate_item.MapLootCrateItem;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.bags.AutoSalvageBag;
import com.robertx22.mine_and_slash.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.loot_bag.ItemLootBag;
import com.robertx22.mine_and_slash.items.bags.map_bag.ItemMapBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.ItemMasterBag;
import com.robertx22.mine_and_slash.items.misc.*;
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
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        putInLists();
        registerSingles(event);
        registerLists(event);

    }

    private static void putInLists() {

        for (GearRarity x : Rarities.Gears.getNormalRarities()) {
            AutoSalvageBag.Items.put(x.Rank(), new AutoSalvageBag(x.Rank()));
            ItemCapacitor.ITEMS.put(x.Rank(), new ItemCapacitor(x.Rank()));
        }

        for (GearRarity rarity : Rarities.Gears.getNormalRarities()
            .stream()
            .filter(x -> x.Rank() > 2)
            .collect(Collectors.toList())) {
            for (ItemLootbox.LootTypes type : ItemLootbox.LootTypes.values()) {
                for (ItemLootbox.LootBoxSizes size : ItemLootbox.LootBoxSizes.values()) {
                    String reg = ItemLootbox.GetStringForType(rarity.Rank(), type, size);
                    ItemLootbox.Items.put(reg,
                        (ItemLootbox) new ItemLootbox(size, type, rarity.Rank()).setRegistryName(reg)
                    );
                }
            }

        }

    }

    private static void registerLists(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        AutoSalvageBag.Items.values()
            .forEach((x) -> r.register(x));
        ItemCapacitor.ITEMS.values()
            .forEach((x) -> r.register(x));
        ItemLootbox.Items.values()
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

        list.add(new MapLootCrateItem());

        list.add(new ItemCurrencyBag().setRegistryName(ItemCurrencyBag.ID));
        list.add(new ItemLootBag().setRegistryName(ItemLootBag.ID));
        list.add(new ItemMapBag().setRegistryName(ItemMapBag.ID));
        list.add(new ItemMasterBag().setRegistryName(ItemMasterBag.ID));

        list.add(new ItemAwakenRuneWord());
        list.add(new ItemIncreaseRarityNearestEntity());
        list.add(new ItemLevelNearestEntity());
        list.add(new ItemNewbieGearBag());

        list.add(new UniqueDungeonKeyItem().setRegistryName(Ref.MODID, "dungeon_key"));

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


