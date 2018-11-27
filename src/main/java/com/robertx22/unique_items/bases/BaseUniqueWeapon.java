package com.robertx22.unique_items.bases;

import java.util.Set;

import com.google.common.collect.Sets;
import com.robertx22.customitems.gearitems.bases.IGearItem;
import com.robertx22.unique_items.IUnique;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseUniqueWeapon extends ItemTool implements IGearItem, IUnique {

	static ItemSword.ToolMaterial Mat = EnumHelper.addToolMaterial("swordmat", 0, 900, 1F, 1F, 10);

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

	public abstract String name();

	public BaseUniqueWeapon() {
		super(Mat, EFFECTIVE_ON);
		IUnique.ITEMS.put(GUID(), this);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return true;
	}

}
