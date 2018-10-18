package com.robertx22.customitems.bases;

import com.robertx22.customitems.oldreplacesoon.NewItemCreator;
import com.robertx22.datasaving.SpellSaving;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class BaseSpellItem extends Item {

	public abstract String Name();

	public abstract String GUID();

	public abstract BaseSpell Spell();

	public BaseSpellItem() {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setCreativeTab(NewItemCreator.MyModTab);
		this.setUnlocalizedName(Name());
		this.setRegistryName(GUID().toLowerCase());

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		SpellItemData data = SpellSaving.Load(playerIn.getHeldItem(handIn));

		if (Spell().CanCast(playerIn, data)) {
			Spell().cast(worldIn, playerIn, handIn, 5, data);
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

}
