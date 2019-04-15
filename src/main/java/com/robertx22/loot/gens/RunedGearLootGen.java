package com.robertx22.loot.gens;

import com.robertx22.generation.RunedGearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.loot.LootUtils;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class RunedGearLootGen extends BaseLootGen {

    GearBlueprint gearPrint;

    public RunedGearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

	gearPrint = new GearBlueprint(mob.getLevel());

    }

    public RunedGearLootGen(float multi, IWorldData world, int level) {
	super(multi, world);
	gearPrint = new GearBlueprint(level);

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.RUNED_GEAR_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	return LootUtils.RandomDamagedGear(RunedGearGen.CreateStack(gearPrint));

    }

}
