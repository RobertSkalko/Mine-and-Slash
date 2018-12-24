package com.robertx22.customitems.infusions.upgrade;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.gearitem.InfusionData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public abstract class BaseUpgradeInfusion extends CurrencyItem implements ICurrencyItemEffect {

    public BaseUpgradeInfusion(String name) {
	super(name);

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
    public ItemStack ModifyItem(ItemStack stack) {

	GearItemData gear = Gear.Load(stack);

	this.TryUpgradeInfusion(gear.infusion);

	Gear.Save(stack, gear);

	return stack;

    }

    @Override
    public boolean CanItemBeModified(ItemStack stack) {
	GearItemData gear = Gear.Load(stack);

	return gear.infusion != null;

    }

    public abstract float critOnSuccessChance();

    public abstract float bonusSuccessChance();

    public abstract float majorFailureChance();

}
