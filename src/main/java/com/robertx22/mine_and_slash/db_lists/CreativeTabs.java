package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemSword;
import com.robertx22.mine_and_slash.items.misc.ItemLootbox;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabs {

    public static final ItemGroup MyModTab = new ItemGroup(Ref.MODID + "_main") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemSword.Items.get(5));
        }

    };

    public static final ItemGroup LootBoxes = new ItemGroup(Ref.MODID + "_lootboxes") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemLootbox.GetItem(5, ItemLootbox.LootTypes.Gear, ItemLootbox.LootBoxSizes.Big));
        }

    };
}
