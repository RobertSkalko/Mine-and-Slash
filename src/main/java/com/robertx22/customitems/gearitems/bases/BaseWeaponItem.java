package com.robertx22.customitems.gearitems.bases;

import java.util.Set;

import com.google.common.collect.Sets;
import com.robertx22.uncommon.SLOC;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseWeaponItem extends ItemTool implements IGearItem, IWeapon {

    static ItemSword.ToolMaterial Mat = EnumHelper.addToolMaterial("swordmat", 0, 900, 1F, 1F, 10);

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public abstract String Name();

    public BaseWeaponItem() {
	super(Mat, EFFECTIVE_ON);
	this.setMaxStackSize(1);
	this.setMaxDamage(BaseArmorItem.MAX_GEAR_DURABILITY);

    }

    public static boolean checkDurability(EntityLivingBase attacker, ItemStack stack) {

	if (stack.getItemDamage() > stack.getMaxDamage() - 20) {
	    attacker.sendMessage(SLOC.chat("low_weapon_durability"));
	    return false;

	}
	return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

	stack.damageItem(1, attacker);

	return true;
    }

}
