package com.robertx22.mine_and_slash.mmorpg.registers.common;

import com.robertx22.mine_and_slash.database.items.runes.*;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemBoots;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemChest;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemHelmet;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemPants;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemBracelet;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemCharm;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemRing;
import com.robertx22.mine_and_slash.items.gearitems.offhands.MyTorch;
import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import com.robertx22.mine_and_slash.items.gearitems.offhands.ShieldRenderer;
import com.robertx22.mine_and_slash.items.gearitems.weapons.*;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GearItemRegisters {

    public static List<Item> items = new ArrayList<Item>();

    private static void register() {

        for (GearRarity rarity : Rarities.Items.rarities()) {

            Item.Properties shieldprop = new Item.Properties().defaultMaxDamage(750);

            DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
                shieldprop.setTEISR(ShieldRenderer::new);
            });

            // 1] class 2] rarity hashmap 3] registry name 4] rarity rank

            int rank = rarity.Rank();

            // runes
            regRune(new CenItem(rank), CenItem.Items, new CenItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new BerItem(rank), BerItem.Items, new BerItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new DosItem(rank), DosItem.Items, new DosItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new GohItem(rank), GohItem.Items, new GohItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new MosItem(rank), MosItem.Items, new MosItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new RahItem(rank), RahItem.Items, new RahItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new VohItem(rank), VohItem.Items, new VohItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new XahItem(rank), XahItem.Items, new XahItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new AnoItem(rank), AnoItem.Items, new AnoItem(rank).genRegisryName()
                    .toLowerCase(), rank);
            regRune(new ItaItem(rank), ItaItem.Items, new ItaItem(rank).genRegisryName()
                    .toLowerCase(), rank);

            // offhands
            regRarities(new NormalShield(rarity.Rank(), shieldprop, "normal_shield" + rarity
                    .Rank()), NormalShield.Items, "shields/normal_shield", rarity.Rank());

            regRarities(new MyTorch(rarity.Rank()), MyTorch.Items, "torch/torch", rarity.Rank());

            //regRarities(new MagicOrb(orbprop, "orb" + rarity.rarity()), MagicOrb.Items, "magic_orb/orb", rarity
            //       .rarity());

            // weapons
            regRarities(new ItemSword(rarity.Rank()), ItemSword.Items, "sword/sword", rarity
                    .Rank());
            regRarities(new ItemHammer(rarity.Rank()), ItemHammer.Items, "hammer/hammer", rarity
                    .Rank());
            regRarities(new ItemAxe(rarity.Rank()), ItemAxe.Items, "axe/axe", rarity.Rank());
            regRarities(new ItemBow(rarity.Rank()), ItemBow.Items, "bow/bow", rarity.Rank());
            regRarities(new ItemStaff(rarity.Rank()), ItemStaff.Items, "staff/staff", rarity
                    .Rank());

            // baubles
            regRarities(new ItemNecklace(rarity.Rank()), ItemNecklace.Items, "necklace/necklace", rarity
                    .Rank());
            regRarities(new ItemBracelet(rarity.Rank()), ItemBracelet.Items, "bracelet/bracelet", rarity
                    .Rank());
            regRarities(new ItemRing(rarity.Rank()), ItemRing.Items, "ring/ring", rarity.Rank());
            regRarities(new ItemCharm(rarity.Rank()), ItemCharm.Items, "charm/charm", rarity
                    .Rank());

            // armors
            regRarities(new ItemBoots(rank), ItemBoots.Items, "boots/boots", rarity.Rank());
            regRarities(new ItemChest(rank), ItemChest.Items, "chest/chest", rarity.Rank());
            regRarities(new ItemHelmet(rank), ItemHelmet.Items, "helmet/helmet", rarity.Rank());
            regRarities(new ItemPants(rank), ItemPants.Items, "pants/pants", rarity.Rank());

            // misc
            regRarities(new ItemMap(rank), ItemMap.Items, "map/map", rarity.Rank()); // not gearitem but yeah

        }

    }

    private static void regRune(Item item, HashMap<Integer, Item> map, String name,
                                int rarity) {
        String reg = name;
        item.setRegistryName(Ref.MODID, reg);
        map.put(rarity, item);
        items.add(item);

    }

    private static void regRarities(Item item, HashMap<Integer, Item> map, String name,
                                    int rarity) {

        String reg = name.toLowerCase() + rarity;
        item.setRegistryName(Ref.MODID, reg);
        map.put(rarity, item);
        items.add(item);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        System.out.println("Registering Gear Items!");

        register();

        for (Item item : items) {
            event.getRegistry().register(item);
        }

    }

}
