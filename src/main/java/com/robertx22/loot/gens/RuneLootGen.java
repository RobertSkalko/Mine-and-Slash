package com.robertx22.loot.gens;

import com.robertx22.generation.RuneGen;
import com.robertx22.generation.blueprints.RuneBlueprint;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class RuneLootGen extends BaseLootGen {

    RuneBlueprint runePrint;

    public RuneLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

	runePrint = new RuneBlueprint(mob.getLevel());

    }

    public RuneLootGen(float multi, IWorldData world, int level) {
	super(multi, world);

	runePrint = new RuneBlueprint(level);

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.RUNE_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	return RuneGen.Create(runePrint);

    }

}
