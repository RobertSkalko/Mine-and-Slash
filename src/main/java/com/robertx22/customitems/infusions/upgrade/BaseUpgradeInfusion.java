package com.robertx22.customitems.infusions.upgrade;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.currency.ICurrencyItemEffect;
import com.robertx22.saveclasses.gearitem.InfusionData;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.entity.EntityLivingBase;

public abstract class BaseUpgradeInfusion extends CurrencyItem implements ICurrencyItemEffect {

    public BaseUpgradeInfusion(String name) {
	super(name);

    }

    public void TryUpgradeInfusion(InfusionData infusion, EntityLivingBase player) {

	if (RandomUtils.roll(this.sucessChance())) {

	    if (RandomUtils.roll(this.critOnSuccessChance())) {
		infusion.majorSuccess(player);

	    } else {
		infusion.success(player);
	    }

	} else {
	    if (RandomUtils.roll(majorFailureChance())) {
		infusion.majorFail(player);
	    } else {
		infusion.fail(player);

	    }
	}

    }

    public abstract float critOnSuccessChance();

    public abstract float sucessChance();

    public abstract float majorFailureChance();

}
