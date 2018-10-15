package com.robertx22.customitems;

import java.util.HashMap;

import com.robertx22.utilityclasses.OnItemCreatedUtils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class BaseCustomItem extends Item {
	public abstract String Name();

	public BaseCustomItem(int rarity, HashMap<Integer, Item> map) {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setCreativeTab(NewItemCreator.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName(Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {

		OnItemCreatedUtils.TryReroll(stack, worldIn);

	}

}
