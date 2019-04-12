package com.robertx22.loot.gens;

import java.util.ArrayList;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.ITiered;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CurrencyLootGen extends BaseLootGen {

    public CurrencyLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

    }

    public CurrencyLootGen(float multi, IWorldData world) {
	super(multi, world);
    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.CURRENCY_DROPRATE;

    }

    @Override
    public ItemStack generateOne() {

	return new ItemStack((Item) RandomUtils
		.WeightedRandom(ListUtils.SameTierOrLess(new ArrayList<ITiered>(CurrencyItem.ITEMS), this.world_tier)));

    }

}
