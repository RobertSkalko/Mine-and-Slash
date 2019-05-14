package com.robertx22.items.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockOre extends BlockBasic {

    Item toDrop;
    int minDropAmount = 1;
    int maxDropAmount = 0;

    public BlockOre(String name, Material material) {
	this(name, material, null, 1, 1);
    }

    public BlockOre(String name, Material material, Item toDrop) {
	this(name, material, toDrop, 1, 1);
    }

    public BlockOre(String name, Material material, Item toDrop, int dropAmount) {
	this(name, material, toDrop, dropAmount, dropAmount);
    }

    public BlockOre(String name, Material material, Item toDrop, int minDropAmount, int maxDropAmount) {
	super(material, name);
	this.toDrop = toDrop;
	this.minDropAmount = minDropAmount;
	this.maxDropAmount = maxDropAmount;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
	return toDrop == null ? Item.getItemFromBlock(this) : toDrop;
    }

    @Override
    public int quantityDropped(Random random) {
	if (this.minDropAmount > this.maxDropAmount) {
	    int i = this.minDropAmount;
	    this.minDropAmount = this.maxDropAmount;
	    this.maxDropAmount = i;
	}
	return this.minDropAmount + random.nextInt(this.maxDropAmount - this.minDropAmount + 1);
    }
}