package com.robertx22.customitems.bases;

import java.util.HashMap;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;
import com.robertx22.uncommon.utilityclasses.OnItemCreatedUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BaseRarityItem extends Item {
	public abstract String Name();

	public BaseRarityItem(int rarity, HashMap<Integer, Item> map) {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setCreativeTab(NewBlocks.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName(Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {

		OnItemCreatedUtils.TryReroll(stack, worldIn);
	}

}
