package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseWeaponItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.AxeWeaponMechanic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAxe extends BaseWeaponItem implements IWeapon {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemAxe() {

    }

    @Override
    public String Name() {
	return "Axe";
    }

    @Override
    public WeaponMechanic mechanic() {
	return new AxeWeaponMechanic();
    }

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(IBlockState blockIn) {
	return blockIn.getBlock() == Blocks.WEB;
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state) {
	Block block = state.getBlock();

	if (block == Blocks.WEB) {
	    return 15.0F;
	} else {
	    Material material = state.getMaterial();
	    return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL
		    && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
	}
    }
}
