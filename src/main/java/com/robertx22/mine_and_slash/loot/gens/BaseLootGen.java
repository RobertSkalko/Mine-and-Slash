package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLootGen {

    public abstract float BaseChance();

    public abstract LootType lootType();

    public boolean hasLevelDistancePunishment() {
        return true;
    }

    protected abstract ItemStack generateOne();

    public boolean condition() {
        return true;
    }

    public List<ItemStack> tryGenerate() {

        List<ItemStack> list = new ArrayList<ItemStack>();

        if (condition()) {
            for (int i = 0; i < info.amount; i++) {
                try {
                    list.add(generateOne());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public LootInfo info;

    public BaseLootGen(LootInfo info) {
        this.info = info;
        this.info.setup(this);
    }

}
