package com.robertx22.items.blocks;

import com.robertx22.db_lists.CreativeTabList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block {

	public BlockBasic(Material material, String name) {

		super(material);

		setCreativeTab(CreativeTabList.MyModTab);
		this.setHardness(2F);
		this.setHarvestLevel("pickaxe", 2);
		this.setResistance(15);
		this.setRegistryName(name);
		this.setTranslationKey(this.getRegistryName().toString());
	}

}
