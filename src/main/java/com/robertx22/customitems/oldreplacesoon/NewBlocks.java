package com.robertx22.customitems.oldreplacesoon;

import com.robertx22.mmorpg.Ref;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class NewBlocks {

	public static final CreativeTabs MyModTab = new CreativeTabs(Ref.NAME) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.DIAMOND_CHESTPLATE);
		}

	};

}
