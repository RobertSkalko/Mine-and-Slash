package com.robertx22.loot.gens;

import com.robertx22.generation.SpellItemGen;
import com.robertx22.generation.blueprints.SpellBlueprint;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class SpellLootGen extends BaseLootGen {

    SpellBlueprint spellPrint;

    public SpellLootGen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	super(mob, player, world, victim);

	spellPrint = new SpellBlueprint(mob.getLevel());

    }

    @Override
    public float BaseChance() {
	return ModConfig.DropRates.SPELL_DROPRATE;
    }

    @Override
    public ItemStack generateOne() {

	return SpellItemGen.Create(spellPrint);

    }

}
