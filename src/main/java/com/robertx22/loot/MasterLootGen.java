package com.robertx22.loot;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.loot.gens.AwakenRuneWordLootGen;
import com.robertx22.loot.gens.CurrencyLootGen;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.loot.gens.MapLootGen;
import com.robertx22.loot.gens.RuneLootGen;
import com.robertx22.loot.gens.RunedGearLootGen;
import com.robertx22.loot.gens.SpellLootGen;
import com.robertx22.loot.gens.UniqueGearLootGen;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class MasterLootGen {

    public static List<ItemStack> gen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
	List<ItemStack> items = new ArrayList<ItemStack>();

	if (mob == null || player == null || world == null || victim == null) {
	    return items;
	}

	if (mob.getLevel() >= ModConfig.Server.CURRENCY_DROP_AFTER_LEVEL) {
	    items.addAll(new CurrencyLootGen(mob, player, world, victim).generate());
	    items.addAll(new AwakenRuneWordLootGen(mob, player, world, victim).generate());
	}
	items.addAll(new GearLootGen(mob, player, world, victim).generate());
	items.addAll(new SpellLootGen(mob, player, world, victim).generate());
	items.addAll(new MapLootGen(mob, player, world, victim).generate());
	items.addAll(new RuneLootGen(mob, player, world, victim).generate());
	items.addAll(new RunedGearLootGen(mob, player, world, victim).generate());

	if (world.isMapWorld()) {
	    items.addAll(new UniqueGearLootGen(mob, player, world, victim).generate());
	}

	return items;
    }

    public static List<ItemStack> gen(float multi, IWorldData world, int level) {
	List<ItemStack> items = new ArrayList<ItemStack>();

	if (world == null) {
	    return items;
	}

	if (level >= ModConfig.Server.CURRENCY_DROP_AFTER_LEVEL) {
	    items.addAll(new CurrencyLootGen(multi, world).generate());
	    items.addAll(new AwakenRuneWordLootGen(multi, world).generate());
	}

	items.addAll(new GearLootGen(multi, world, level).generate());
	items.addAll(new SpellLootGen(multi, world, level).generate());
	items.addAll(new MapLootGen(multi, world, level).generate());
	items.addAll(new RuneLootGen(multi, world, level).generate());
	items.addAll(new RunedGearLootGen(multi, world, level).generate());

	if (world.isMapWorld()) {
	    items.addAll(new UniqueGearLootGen(multi, world, level).generate());

	}

	return items;
    }

    public static void genAndDrop(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {

	List<ItemStack> items = gen(mob, player, world, victim);

	for (ItemStack stack : items) {
	    victim.entityDropItem(stack, 1F);
	}

    }

}
