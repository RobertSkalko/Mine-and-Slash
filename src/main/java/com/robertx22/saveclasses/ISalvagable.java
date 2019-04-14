package com.robertx22.saveclasses;

import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public interface ISalvagable {

    ItemStack getSalvageResult(float salvageBonus);

    int getSalvagedRarity();

    public default int tryIncreaseAmount(float salvageBonus, int amount) {

	if (RandomUtils.roll((salvageBonus - 1) * 100)) {
	    return amount + 1;
	}

	return amount;
    }
}
