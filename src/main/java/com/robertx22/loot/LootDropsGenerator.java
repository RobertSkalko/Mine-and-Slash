package com.robertx22.loot;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.MobRarity;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LootDropsGenerator {

	private static float GearChance = 550F;
	private static float CurrencyChance = 585.5F;

	public static void Generate(Unit mob, Unit player, EntityLivingBase mobEntity) {

		float playerFindItemsChance = 0;

		if (player != null) {

		}

		List<ItemStack> items = new ArrayList<ItemStack>();

		MobRarity rarity = Rarities.Mobs.get(mob.rarity);

		float FinalGearChance = ApplyMobLootMulti(GearChance, mob, playerFindItemsChance);
		float FinalCurrencyChance = ApplyMobLootMulti(CurrencyChance, mob, playerFindItemsChance);

		int GearDrops = WhileRoll(FinalGearChance);
		int CurrencyDrops = WhileRoll(FinalCurrencyChance);

		GearBlueprint gearPrint = new GearBlueprint(mob.level);

		for (int i = 0; i < GearDrops; i++) {
			items.add(RandomDamagedGear(GearGen.Create(gearPrint)));
		}

		for (int i = 0; i < CurrencyDrops; i++) {
			Item item = (Item) RandomUtils.WeightedRandom(ListUtils.CollectionToList(CurrencyItem.ITEMS));
			items.add(new ItemStack(item));
		}

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) != null) {
				mobEntity.entityDropItem(items.get(i), 1F);
			}
		}

	}

	private static ItemStack RandomDamagedGear(ItemStack stack) {
		if (stack.getMaxDamage() > 0) {
			float damage = (float) RandomUtils.RandomRange(70, 95) / (float) 100;
			stack.setItemDamage((int) (damage * stack.getMaxDamage()));
		}

		return stack;
	}

	private static float ApplyMobLootMulti(float chance, Unit mob, float playerFind) {
		return chance * Rarities.Mobs.get(mob.rarity).LootMultiplier() + (mob.vanillaHP / 15) * (1 + playerFind);
	}

	private static int WhileRoll(float chance) {
		int amount = 0;

		while (chance > 0) {

			float maxChance = 70F;

			float currentChance = chance;

			if (currentChance > maxChance) {
				currentChance = maxChance;
			}

			chance -= currentChance;

			if (RandomUtils.roll(currentChance)) {
				amount++;
			}

		}
		return amount;

	}

}
