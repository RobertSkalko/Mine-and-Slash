package com.robertx22.loot.gens;

import com.robertx22.generation.MapGen;
import com.robertx22.generation.blueprints.MapBlueprint;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class MapLootGen extends BaseLootGen {
    MapBlueprint blueprint;

    public MapLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

	blueprint = new MapBlueprint(mob.getLevel(), world.getTier());

    }

    public MapLootGen(float multi, IWorldData world, int level) {
	super(multi, world);

	blueprint = new MapBlueprint(level, world.getTier());

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.MAP_DROPRATE;
    }

    @Override
    public boolean hasLevelDistancePunishment() {
	return false;
    }

    @Override
    public ItemStack generateOne() {

	return MapGen.Create(blueprint);
    }

}
