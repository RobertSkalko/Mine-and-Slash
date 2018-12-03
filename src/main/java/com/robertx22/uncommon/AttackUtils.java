package com.robertx22.uncommon;

import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class AttackUtils {

    public static void Attack(EntityLivingBase source, EntityLivingBase target, ItemStack weapon, UnitData sourceData) {

	if (source instanceof EntityPlayer) {

	    UnitData targetData = Load.Unit(target);

	    if (weapon != null && !weapon.isEmpty() && weapon.getItem() instanceof IWeapon) {

		WeaponMechanic iWep = ((IWeapon) weapon.getItem()).mechanic();

		int energyCost = iWep.GetEnergyCost();

		if (sourceData.getUnit().hasEnoughEnergy(energyCost) == false) {
		    NoEnergyMessage(source);

		    return;

		} else {
		    sourceData.getUnit().SpendEnergy(energyCost);
		    weapon.damageItem(1, source);
		    iWep.Attack(source, target, sourceData, targetData);

		}

	    }
	}
    }

    public static void NoEnergyMessage(EntityLivingBase entity) {
	entity.sendMessage(new TextComponentString(TextFormatting.RED + "Not Enough Energy."));
    }
}
