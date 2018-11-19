package com.robertx22.customitems.gearitems.bases;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.datasaving.Spell;

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
		this.setUnlocalizedName(Name());
		this.setRegistryName(GUID().toLowerCase());

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		SpellItemData data = Spell.Load(stack);

		if (data != null) {

			ItemRarity rarity = Rarities.Items.get(data.rarity);

			tooltip.clear();

			tooltip.add(Rarities.Items.get(data.rarity).Color() + Spell().Name());
			tooltip.add(TextFormatting.YELLOW + "Level: " + data.level);
			tooltip.add("");

			tooltip.add(TextFormatting.GREEN + "Stats:");
			tooltip.add(" * " + (TextFormatting.RED + data.GetManaDesc()));
			tooltip.add(" * " + (TextFormatting.RED + data.GetBaseDesc()));
			tooltip.add(" * " + (TextFormatting.RED + data.GetScalingDesc()));

			tooltip.add("");

			tooltip.add(TextFormatting.LIGHT_PURPLE + data.GetSpell().GetDescription(data));

			tooltip.add("");
			tooltip.add(rarity.Color() + "Rarity: " + rarity.Name());
		}
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

		try {
			SpellItemData data = Spell.Load(playerIn.getHeldItem(handIn));

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
