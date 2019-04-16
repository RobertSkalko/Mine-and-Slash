package com.robertx22.loot.gens;

import com.robertx22.generation.RunedGearGen;
import com.robertx22.generation.blueprints.RunedGearBlueprint;
import com.robertx22.loot.LootUtils;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Gear;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class RunedGearLootGen extends BaseLootGen {

    RunedGearBlueprint gearPrint;

    public RunedGearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

	gearPrint = new RunedGearBlueprint(mob.getLevel());

    }

    public RunedGearLootGen(float multi, IWorldData world, int level) {
	super(multi, world);
	gearPrint = new RunedGearBlueprint(level);

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.RUNED_GEAR_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	ItemStack stack = RunedGearGen.CreateStack(gearPrint);

	GearItemData gear = Gear.Load(stack);

	return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
