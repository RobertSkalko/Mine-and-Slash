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

			// weapons
			regRarities(new ItemSword(), ItemSword.Items, "sword", rarity.Rank());
			regRarities(new ItemHammer(), ItemHammer.Items, "hammer", rarity.Rank());
			regRarities(new ItemAxe(), ItemAxe.Items, "axe", rarity.Rank());
			regRarities(new ItemBow(), ItemBow.Items, "bow/bow", rarity.Rank());
			regRarities(new ItemStaff(), ItemStaff.Items, "staff", rarity.Rank());

			// baubles
			regRarities(new ItemNecklace(), ItemNecklace.Items, "necklace", rarity.Rank());
			regRarities(new ItemBracelet(), ItemBracelet.Items, "bracelet", rarity.Rank());
			regRarities(new ItemRing(), ItemRing.Items, "ring", rarity.Rank());
			regRarities(new ItemCharm(), ItemCharm.Items, "charm", rarity.Rank());

			// armors
			regRarities(new ItemBoots(rank), ItemBoots.Items, "boots", rarity.Rank());
			regRarities(new ItemChest(rank), ItemChest.Items, "chest", rarity.Rank());
			regRarities(new ItemHelmet(rank), ItemHelmet.Items, "helmet", rarity.Rank());
			regRarities(new ItemPants(rank), ItemPants.Items, "pants", rarity.Rank());

			// misc
			regRarities(new ItemMap(), ItemMap.Items, "map", rarity.Rank()); // not gearitem but yeah

		}

	}

	private static void regRarities(Item item, HashMap<Integer, Item> map, String name, int rarity) {

		String reg = name + rarity;
		item.setRegistryName(reg).setUnlocalizedName(reg);
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
