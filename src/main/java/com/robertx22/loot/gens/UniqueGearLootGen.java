package com.robertx22.loot.gens;

import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.UniqueBlueprint;
import com.robertx22.loot.LootUtils;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Gear;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class UniqueGearLootGen extends BaseLootGen {
    UniqueBlueprint gearPrint;

    public UniqueGearLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);
	gearPrint = new UniqueBlueprint(mob.getLevel(), this.world_tier, true);

    }

    public UniqueGearLootGen(float multi, IWorldData world, int level) {
	super(multi, world);
	gearPrint = new UniqueBlueprint(level, this.world_tier, true);

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.UNIQUE_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	ItemStack stack = UniqueGearGen.CreateStack(gearPrint);

	GearItemData gear = Gear.Load(stack);

	return LootUtils.RandomDamagedGear(stack, gear.GetRarity());

    }

}
