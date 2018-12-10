package com.robertx22.loot.gens;

import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.loot.LootUtils;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class GearLootGen extends BaseLootGen {

    GearBlueprint gearPrint;

    public GearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

	gearPrint = new GearBlueprint(mob.getLevel());

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.GEAR_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	return LootUtils.RandomDamagedGear(GearGen.CreateStack(gearPrint));

    }

}
