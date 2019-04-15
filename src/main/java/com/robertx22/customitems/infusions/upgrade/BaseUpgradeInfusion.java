package com.robertx22.customitems.infusions.upgrade;

import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.InfusionData;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseUpgradeInfusion extends CurrencyItem implements ICurrencyItemEffect {

    public BaseUpgradeInfusion(String name) {
	super(name);

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
	super.addInformation(stack, worldIn, tooltip, flagIn);

	tooltip.add("");
	tooltip.add(TextFormatting.GOLD + CLOC.word("bonus_success_rate") + ": " + this.bonusSuccessChance() + "%");
	tooltip.add(TextFormatting.GOLD + CLOC.word("major_success_bonus") + ": " + this.critOnSuccessChance() + "%");
	tooltip.add(TextFormatting.GOLD + CLOC.word("major_failure_chance") + ": " + this.majorFailureChance() + "%");

    }

    public void TryUpgradeInfusion(InfusionData infusion) {

	if (RandomUtils.roll(infusion.getChance() + this.bonusSuccessChance())) {

	    if (RandomUtils.roll(this.critOnSuccessChance())) {
		infusion.majorSuccess();

	    } else {
		infusion.success();
	    }

	} else {
	    if (RandomUtils.roll(majorFailureChance())) {
		infusion.majorFail();
	    } else {
		infusion.fail();

	    }
	}

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

	GearItemData gear = Gear.Load(stack);

	this.TryUpgradeInfusion(gear.infusion);

	Gear.Save(stack, gear);

	return stack;

    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {
	GearItemData gear = Gear.Load(stack);

	return gear.infusion != null && gear.infusion.canUpgrade();

    }

    public abstract float critOnSuccessChance();

    public abstract float bonusSuccessChance();

    public abstract float majorFailureChance();

}
