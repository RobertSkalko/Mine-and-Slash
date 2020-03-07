package com.robertx22.mine_and_slash.mmorpg;

import com.google.common.collect.Lists;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RepairItemRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;

public class RepairItemRecipeOverride extends RepairItemRecipe {
    public RepairItemRecipeOverride(ResourceLocation loc) {
        super(loc);
    }

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        List<ItemStack> list = Lists.newArrayList();

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                list.add(itemstack);
                if (list.size() > 1) {
                    ItemStack itemstack1 = list.get(0);
                    if (itemstack.getItem() != itemstack1.getItem() || itemstack1.getCount() != 1 || itemstack.getCount() != 1 || !itemstack1.isRepairable()) {
                        return false;
                    }
                }
            }
        }

        for (ItemStack stack : list) {
            if (Gear.has(stack)) {
                return false; // deny repair crafting of stat gear!!
            }
        }

        return list.size() == 2;
    }
}
