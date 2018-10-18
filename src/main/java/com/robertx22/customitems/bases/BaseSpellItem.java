package com.robertx22.customitems.bases;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.robertx22.customitems.oldreplacesoon.NewItemCreator;
import com.robertx22.database.lists.Rarities;
import com.robertx22.datasaving.SpellSaving;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		SpellItemData data = SpellSaving.Load(stack);

		if (data != null) {

			tooltip.clear();

			tooltip.add(Spell().Name());
			tooltip.add(TextFormatting.YELLOW + "Level: " + data.level);

			tooltip.add("");

			ItemRarity rarity = Rarities.Items.get(data.rarity);
			tooltip.add(rarity.Color() + "Rarity: " + rarity.Name());
		}
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		try {
			SpellItemData data = SpellSaving.Load(playerIn.getHeldItem(handIn));

			if (data != null) {
				if (Spell().CanCast(playerIn, data)) {
					Spell().cast(worldIn, playerIn, handIn, 5, data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

}
