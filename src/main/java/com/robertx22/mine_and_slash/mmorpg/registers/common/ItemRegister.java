package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.items.currency.*;
import com.robertx22.mine_and_slash.database.items.spell_items.ItemBonusEleAtkDmg;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_bomb_proj.ItemAcidBomb;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_bomb_proj.ItemFireBomb;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_bomb_proj.ItemIceBomb;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_bomb_proj.ItemThunderBomb;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_projectile.ItemAcidExplosion;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_projectile.ItemFlameExplosion;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_projectile.ItemFrostExplosion;
import com.robertx22.mine_and_slash.database.items.spell_items.aoe_projectile.ItemLightningExplosion;
import com.robertx22.mine_and_slash.database.items.spell_items.nova.ItemFireNova;
import com.robertx22.mine_and_slash.database.items.spell_items.nova.ItemFrostNova;
import com.robertx22.mine_and_slash.database.items.spell_items.nova.ItemPoisonNova;
import com.robertx22.mine_and_slash.database.items.spell_items.nova.ItemThunderNova;
import com.robertx22.mine_and_slash.database.items.spell_items.projectile.ItemAcidBolt;
import com.robertx22.mine_and_slash.database.items.spell_items.projectile.ItemFireBolt;
import com.robertx22.mine_and_slash.database.items.spell_items.projectile.ItemFrostBolt;
import com.robertx22.mine_and_slash.database.items.spell_items.projectile.ItemThunderBolt;
import com.robertx22.mine_and_slash.database.items.spell_items.self.ItemInstantHeal;
import com.robertx22.mine_and_slash.database.items.spell_items.self.ItemSelfRegen;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.bags.AutoSalvageBag;
import com.robertx22.mine_and_slash.items.bags.currency_bag.ItemCurrencyBag;
import com.robertx22.mine_and_slash.items.bags.loot_bag.ItemLootBag;
import com.robertx22.mine_and_slash.items.bags.map_bag.ItemMapBag;
import com.robertx22.mine_and_slash.items.bags.master_bag.ItemMasterBag;
import com.robertx22.mine_and_slash.items.misc.*;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.profession_items.AlchemyItemRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegister {

    @ObjectHolder(ItemOrbOfTransmutation.ID)
    public static CurrencyItem ORB_OF_TRANSMUTATION;
    @ObjectHolder(ItemChaosOrb.ID)
    public static CurrencyItem CHAOS_ORB;
    @ObjectHolder(ItemAddPrefix.ID)
    public static CurrencyItem ADD_PREFIX;
    @ObjectHolder(ItemAddSuffix.ID)
    public static CurrencyItem ADD_SUFFIX;
    @ObjectHolder(ItemStoneOfHope.ID)
    public static CurrencyItem STONE_OF_HOPE;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        putInLists();
        registerSingles(event);
        registerLists(event);
        regProfessionItems(event);
    }

    private static void regProfessionItems(RegistryEvent.Register<Item> event) {
        AlchemyItemRegister.register(event);
    }

    private static void putInLists() {

        for (GearRarity x : Rarities.Items.rarities()) {
            AutoSalvageBag.Items.put(x.Rank(), new AutoSalvageBag(x.Rank()));
            ItemCapacitor.Items.put(x.Rank(), new ItemCapacitor(x.Rank()));

            for (GearRarity rarity : Rarities.Items.rarities()) {
                for (ItemLootbox.LootTypes type : ItemLootbox.LootTypes.values()) {
                    for (ItemLootbox.LootBoxSizes size : ItemLootbox.LootBoxSizes.values()) {
                        String reg = ItemLootbox.GetStringForType(rarity.Rank(), type, size);
                        ItemLootbox.Items.put(reg, (ItemLootbox) new ItemLootbox(size, type, rarity
                                .Rank()).setRegistryName(reg));
                    }
                }
            }

        }

    }

    private static void registerLists(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        AutoSalvageBag.Items.values().forEach((x) -> r.register(x));
        ItemCapacitor.Items.values().forEach((x) -> r.register(x));
        ItemLootbox.Items.values().forEach((x) -> r.register(x));

        for (IUnique uniq : SlashRegistry.UniqueGears().getList()) {
            Item item = (Item) uniq;
            item.setRegistryName("uniques/" + uniq.getGearSlot().
                    resourceID() + "/" + uniq.GUID());
            r.register(item);
        }

        SlashRegistry.UniqueRunes()
                .getList()
                .forEach(x -> r.register(x.setRegistryName(x.genRegisryName())));

    }

    private static List<Item> list = new ArrayList<>();

    public static void shcheduleToRegister(Item item) {
        list.add(item);
    }

    private static void registerSingles(RegistryEvent.Register<Item> event) {

        IForgeRegistry<Item> r = event.getRegistry();

        list.add(new ItemCurrencyBag().setRegistryName(ItemCurrencyBag.ID));
        list.add(new ItemLootBag().setRegistryName(ItemLootBag.ID));
        list.add(new ItemMapBag().setRegistryName(ItemMapBag.ID));
        list.add(new ItemMasterBag().setRegistryName(ItemMasterBag.ID));

        list.add(new ItemAwakenRuneWord());
        list.add(new ItemIncreaseRarityNearestEntity());
        list.add(new ItemLevelNearestEntity());
        list.add(new ItemMapBackPortal());
        list.add(new ItemNewbieGearBag());
        list.add(new ItemPlayerLevelUp());

        list.add(new ItemAcidBolt());
        list.add(new ItemFireBolt());
        list.add(new ItemFrostBolt());
        list.add(new ItemThunderBolt());

        list.add(new ItemAcidBomb());
        list.add(new ItemFireBomb());
        list.add(new ItemIceBomb());
        list.add(new ItemThunderBomb());

        list.add(new ItemFireNova());
        list.add(new ItemFrostNova());
        list.add(new ItemThunderNova());
        list.add(new ItemPoisonNova());

        list.add(new ItemAcidExplosion());
        list.add(new ItemFrostExplosion());
        list.add(new ItemLightningExplosion());
        list.add(new ItemFlameExplosion());

        list.add(new ItemInstantHeal());
        list.add(new ItemSelfRegen());

        list.add(new ItemBonusEleAtkDmg(Elements.Physical));

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


