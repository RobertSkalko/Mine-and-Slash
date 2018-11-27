package com.robertx22.mmorpg.registers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.weapons.ItemAxe;
import com.robertx22.customitems.gearitems.weapons.ItemBow;
import com.robertx22.customitems.gearitems.weapons.ItemHammer;
import com.robertx22.customitems.gearitems.weapons.ItemStaff;
import com.robertx22.customitems.gearitems.weapons.ItemSword;
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

			regRarities(new ItemSword(), ItemSword.Items, "sword", rarity.Rank());
			regRarities(new ItemHammer(), ItemHammer.Items, "hammer", rarity.Rank());
			regRarities(new ItemAxe(), ItemAxe.Items, "axe", rarity.Rank());
			regRarities(new ItemBow(), ItemBow.Items, "bow", rarity.Rank());
			regRarities(new ItemStaff(), ItemStaff.Items, "staff", rarity.Rank());

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
