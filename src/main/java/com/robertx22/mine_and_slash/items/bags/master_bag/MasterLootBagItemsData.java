package com.robertx22.mine_and_slash.items.bags.master_bag;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Map;

public class MasterLootBagItemsData {

    public MasterLootBagItemsData() {

    }

    public HashMap<String, ItemStackHandler> getItems() {

        HashMap<String, ItemStackHandler> map = new HashMap<>();

        for (Map.Entry<String, CompoundNBT> entry : items.entrySet()) {

            ItemStackHandler itemhandler = new ItemStackHandler();
            itemhandler.deserializeNBT(entry.getValue());

            map.put(entry.getKey(), itemhandler);

        }

        return map;

    }

    public HashMap<String, CompoundNBT> items = new HashMap<>();

}
