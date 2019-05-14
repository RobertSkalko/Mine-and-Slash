package com.robertx22.db_lists;

import com.robertx22.items.bags.loot_bag.ItemLootBag;
import com.robertx22.items.currency.ItemChaosOrb;
import com.robertx22.items.gearitems.weapons.ItemSword;
import com.robertx22.mmorpg.Ref;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabList {

	public static final CreativeTabs MyModTab = new CreativeTabs(Ref.MODID + "_main") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemSword.Items.get(5));
		}

	};

	public static final CreativeTabs UniqueItems = new CreativeTabs(Ref.MODID + "_uniques") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemSword.Items.get(2));
		}

	};

	public static final CreativeTabs CurrencyTab = new CreativeTabs(Ref.MODID + "_currency") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemChaosOrb.ITEM);
		}

	};

	public static final CreativeTabs LootboxTab = new CreativeTabs(Ref.MODID + "_lootboxes") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ItemLootBag.ITEM);
		}

	};

}
