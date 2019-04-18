package com.robertx22.loot.gens;

import com.robertx22.generation.AwakenRuneWordGen;
import com.robertx22.generation.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class AwakenRuneWordLootGen extends BaseLootGen {

    public AwakenRuneWordLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

    }

    public AwakenRuneWordLootGen(float multi, IWorldData world) {
	super(multi, world);
    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.AWAKEN_RUNEWORD_DROPRATE;

    }

    @Override
    public ItemStack generateOne() {

	return AwakenRuneWordGen.Create(new AwakenRuneWordBlueprint());

    }

}
