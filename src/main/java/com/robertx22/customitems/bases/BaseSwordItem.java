package com.robertx22.customitems.bases;

import java.util.HashMap;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseSwordItem extends ItemSword {

	static ItemSword.ToolMaterial Mat = EnumHelper.addToolMaterial("swordmat", 0, 900, 1F, 1F, 1);

	public abstract String Name();

	public BaseSwordItem(int rarity, HashMap<Integer, Item> map) {
		super(Mat);
		this.setMaxStackSize(1);
		this.setMaxDamage(1000);
		this.setCreativeTab(NewBlocks.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName(Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

	/*
	 * int energyCost = 3;
	 * 
	 * @Override public boolean hitEntity(ItemStack stack, EntityLivingBase target,
	 * EntityLivingBase attacker) {
	 * 
	 * if (attacker instanceof EntityPlayer) { Unit unit =
	 * UnitSaving.Load(attacker); if (unit.energy().GetCurrentValue() < energyCost)
	 * { ((EntityPlayer) attacker) .sendMessage(new
	 * TextComponentString(TextFormatting.RED + "Not Enough Energy."));
	 * 
	 * return false; } else { unit.SpendEnergy(energyCost);
	 * UnitSaving.Save(attacker, unit); }
	 * 
	 * }
	 * 
	 * stack.damageItem(1, attacker); return true; }
	 * 
	 */
}
