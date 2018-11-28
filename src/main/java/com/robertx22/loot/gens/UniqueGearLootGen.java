package com.robertx22.loot.gens;

import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.loot.LootUtils;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen {
    GearBlueprint gearPrint;

    public UniqueGearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);
	gearPrint = new GearBlueprint(mob.getLevel());
	gearPrint.tier = this.world_tier;

    }

    @Override
    public float BaseChance() {
	return 0.5F;
    }

    @Override
    public ItemStack generateOne() {

	return LootUtils.RandomDamagedGear(UniqueGearGen.CreateStack(gearPrint));

    }

}
