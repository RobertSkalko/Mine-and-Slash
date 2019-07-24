package com.robertx22.mine_and_slash.items.ores;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.List;

public class BlockOre extends Block {

    int minDropAmount = 1;
    int maxDropAmount = 3;

    int rarity;

    public BlockOre(int rarity, Material material) {
        super(Block.Properties.create(material).hardnessAndResistance(2F));
        this.rarity = rarity;

        this.setRegistryName(Ref.MODID + ":ore_block" + rarity);

    }

    @Override
    public List<ItemStack> getDrops(BlockState p_220076_1_, LootContext.Builder loot) {
        if (this.minDropAmount > this.maxDropAmount) {
            int i = this.minDropAmount;
            this.minDropAmount = this.maxDropAmount;
            this.maxDropAmount = i;
        }

        int amount = this.minDropAmount + this.RANDOM.nextInt(this.maxDropAmount - this.minDropAmount + 1);

        List<ItemStack> list = new ArrayList<>();

        ItemStack ore = new ItemStack(ItemOre.ItemOres.get(rarity));
        ore.setCount(amount);

        list.add(ore);

        return list;

    }

}