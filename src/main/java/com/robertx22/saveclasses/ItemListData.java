package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

@Storable
public class ItemListData {

    public ItemListData() {

    }

    public ItemListData(List<ItemStack> items) {
	this.items = items;
    }

    @Store
    public List<ItemStack> items = new ArrayList<ItemStack>();

}
