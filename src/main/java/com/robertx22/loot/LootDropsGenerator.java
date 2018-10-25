package com.robertx22.loot;

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

	private static float GearChance = 50F;
	private static float CurrencyChance = 85.5F;

	public static void Generate(Unit mob, Unit player, EntityLivingBase mobEntity) {

		MobRarity rarity = Rarities.Mobs.get(mob.rarity);

		float FinalGearChance = ApplyMobLootMulti(GearChance, mob);
		float FinalCurrencyChance = ApplyMobLootMulti(CurrencyChance, mob);

		int GearDrops = WhileRoll(FinalGearChance);
		int CurrencyDrops = WhileRoll(FinalCurrencyChance);

		GearBlueprint gearPrint = new GearBlueprint(mob.level);

		for (int i = 0; i < GearDrops; i++) {

			mobEntity.entityDropItem(GearGen.Create(gearPrint), 1F);
		}

		for (int i = 0; i < CurrencyDrops; i++) {
			Item item = (Item) RandomUtils.WeightedRandom(ListUtils.CollectionToList(CurrencyItem.ITEMS));
			mobEntity.entityDropItem(new ItemStack(item), 1F);
		}

	}

	private static float ApplyMobLootMulti(float chance, Unit mob) {
		return chance * Rarities.Mobs.get(mob.rarity).LootMultiplier() + (mob.vanillaHP / 15);
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
