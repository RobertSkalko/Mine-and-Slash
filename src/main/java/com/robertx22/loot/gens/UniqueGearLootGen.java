package com.robertx22.loot.gens;

import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.UniqueBlueprint;
import com.robertx22.loot.LootUtils;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen {
    UniqueBlueprint gearPrint;

    public UniqueGearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);
	gearPrint = new UniqueBlueprint(mob.getLevel(), this.world_tier, true);

    }

    @Override
    public float BaseChance() {
	return 0.15F;
    }

    @Override
    public ItemStack generateOne() {

	return LootUtils.RandomDamagedGear(UniqueGearGen.CreateStack(gearPrint));

    }

}
