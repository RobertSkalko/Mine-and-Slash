package com.robertx22.mmorpg.registers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.armor.ItemBoots;
import com.robertx22.customitems.gearitems.armor.ItemChest;
import com.robertx22.customitems.gearitems.armor.ItemHelmet;
import com.robertx22.customitems.gearitems.armor.ItemPants;
import com.robertx22.customitems.gearitems.baubles.ItemBracelet;
import com.robertx22.customitems.gearitems.baubles.ItemCharm;
import com.robertx22.customitems.gearitems.baubles.ItemNecklace;
import com.robertx22.customitems.gearitems.baubles.ItemRing;
import com.robertx22.customitems.gearitems.weapons.ItemAxe;
import com.robertx22.customitems.gearitems.weapons.ItemBow;
import com.robertx22.customitems.gearitems.weapons.ItemHammer;
import com.robertx22.customitems.gearitems.weapons.ItemStaff;
import com.robertx22.customitems.gearitems.weapons.ItemSword;
import com.robertx22.customitems.misc.ItemMap;
import com.robertx22.customitems.runes.AnoItem;
import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.DosItem;
import com.robertx22.customitems.runes.GohItem;
import com.robertx22.customitems.runes.ItaItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.RahItem;
import com.robertx22.customitems.runes.VohItem;
import com.robertx22.customitems.runes.XahItem;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class GearItemRegisters {

    private static List<Item> items = new ArrayList<Item>();

    public static void register() {

	for (ItemRarity rarity : Rarities.Items) {

	    // 1] class 2] rarity hashmap 3] registry name 4] rarity rank

	    int rank = rarity.Rank();

	    // runes
	    regRarities(new CenItem(rank), CenItem.Items, "runes/" + new CenItem(rank).name().toLowerCase(), rank);
	    regRarities(new BerItem(rank), BerItem.Items, "runes/" + new BerItem(rank).name().toLowerCase(), rank);
	    regRarities(new DosItem(rank), DosItem.Items, "runes/" + new DosItem(rank).name().toLowerCase(), rank);
	    regRarities(new GohItem(rank), GohItem.Items, "runes/" + new GohItem(rank).name().toLowerCase(), rank);
	    regRarities(new MosItem(rank), MosItem.Items, "runes/" + new MosItem(rank).name().toLowerCase(), rank);
	    regRarities(new RahItem(rank), RahItem.Items, "runes/" + new RahItem(rank).name().toLowerCase(), rank);
	    regRarities(new VohItem(rank), VohItem.Items, "runes/" + new VohItem(rank).name().toLowerCase(), rank);
	    regRarities(new XahItem(rank), XahItem.Items, "runes/" + new XahItem(rank).name().toLowerCase(), rank);
	    regRarities(new AnoItem(rank), AnoItem.Items, "runes/" + new AnoItem(rank).name().toLowerCase(), rank);
	    regRarities(new ItaItem(rank), ItaItem.Items, "runes/" + new ItaItem(rank).name().toLowerCase(), rank);

	    // weapons
	    regRarities(new ItemSword(), ItemSword.Items, "sword/sword", rarity.Rank());
	    regRarities(new ItemHammer(), ItemHammer.Items, "hammer/hammer", rarity.Rank());
	    regRarities(new ItemAxe(), ItemAxe.Items, "axe/axe", rarity.Rank());
	    regRarities(new ItemBow(), ItemBow.Items, "bow/bow", rarity.Rank());
	    regRarities(new ItemStaff(), ItemStaff.Items, "staff/staff", rarity.Rank());

	    // baubles
	    regRarities(new ItemNecklace(), ItemNecklace.Items, "necklace/necklace", rarity.Rank());
	    regRarities(new ItemBracelet(), ItemBracelet.Items, "bracelet/bracelet", rarity.Rank());
	    regRarities(new ItemRing(), ItemRing.Items, "ring/ring", rarity.Rank());
	    regRarities(new ItemCharm(), ItemCharm.Items, "charm/charm", rarity.Rank());

	    // armors
	    regRarities(new ItemBoots(rank), ItemBoots.Items, "boots/boots", rarity.Rank());
	    regRarities(new ItemChest(rank), ItemChest.Items, "chest/chest", rarity.Rank());
	    regRarities(new ItemHelmet(rank), ItemHelmet.Items, "helmet/helmet", rarity.Rank());
	    regRarities(new ItemPants(rank), ItemPants.Items, "pants/pants", rarity.Rank());

	    // misc
	    regRarities(new ItemMap(), ItemMap.Items, "map/map", rarity.Rank()); // not gearitem but yeah

	}

    }

    private static void regRarities(Item item, HashMap<Integer, Item> map, String name, int rarity) {

	String reg = name + rarity;
	item.setRegistryName(reg);
	item.setUnlocalizedName(item.getRegistryName().toString());
	map.put(rarity, item);
	items.add(item);

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	for (Item item : items) {
	    event.getRegistry().register(item);
	}

    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	for (Item item : items) {
	    RegisterUtils.registerRender(item);
	}
    }

}
