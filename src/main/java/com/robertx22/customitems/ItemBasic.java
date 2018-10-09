package com.robertx22.customitems;

import net.minecraft.item.Item;

class ItemBasic extends Item {

    public ItemBasic(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setMaxDamage(0);
        maxStackSize = 64;
        setCreativeTab(NewItemCreator.MyModTab);
    }

}