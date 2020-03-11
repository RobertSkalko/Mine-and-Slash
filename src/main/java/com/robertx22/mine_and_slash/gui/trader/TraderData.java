package com.robertx22.mine_and_slash.gui.trader;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Storable
public class TraderData {

    @Store
    List<ItemStack> stacks = new ArrayList<>();

    @Store
    public boolean generated = false;

}
