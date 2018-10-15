package com.robertx22.customitems.blocks;

import com.robertx22.customitems.NewItemCreator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

	public BlockBasic(Material material, String name) {

		super(material);

		setCreativeTab(NewItemCreator.MyModTab);
		this.setHardness(2F);
		this.setHarvestLevel("pickaxe", 3);
		this.setResistance(15);
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
	}

}
