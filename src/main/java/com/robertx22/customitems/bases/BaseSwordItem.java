package com.robertx22.customitems.bases;

import java.util.HashMap;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseSwordItem extends ItemSword {

	static ItemSword.ToolMaterial Mat = EnumHelper.addToolMaterial("swordmat", 0, 900, 1F, 1F, 10);

	public abstract String Name();

	public BaseSwordItem(int rarity, HashMap<Integer, Item> map) {
		super(Mat);
		this.setMaxStackSize(1);
		this.setMaxDamage(BaseArmorItem.MAX_GEAR_DURABILITY);
		this.setCreativeTab(NewBlocks.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName(Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

}
