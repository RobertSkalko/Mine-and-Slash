package com.robertx22.loot;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.loot.gens.CurrencyLootGen;
import com.robertx22.loot.gens.GearLootGen;
import com.robertx22.loot.gens.MapLootGen;
import com.robertx22.loot.gens.SpellLootGen;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class MasterLootGen {
	public static List<ItemStack> gen(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {
		List<ItemStack> items = new ArrayList();

		if (mob == null || player == null || world == null || victim == null) {
			return items;
		}

		items.addAll(new CurrencyLootGen(mob, player, world, victim).generate());
		items.addAll(new GearLootGen(mob, player, world, victim).generate());
		items.addAll(new SpellLootGen(mob, player, world, victim).generate());
		items.addAll(new MapLootGen(mob, player, world, victim).generate());

		return items;
	}

	public static void genAndDrop(UnitData mob, UnitData player, IWorldData world, EntityLivingBase victim) {

		List<ItemStack> items = gen(mob, player, world, victim);

		for (ItemStack stack : items) {
			victim.entityDropItem(stack, 1F);
		}

	}

}
