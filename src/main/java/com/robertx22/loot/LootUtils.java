package com.robertx22.loot;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.SpellItemGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.generation.blueprints.SpellBlueprint;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LootUtils {

	public static float GearChance = 7.5F;
	public static float CurrencyChance = 1.8F;
	public static float SpellChance = 2.5F;

	public static void Generate(UnitData mob, UnitData player, EntityLivingBase mobEntity, IWorldData world) {

		if (player != null) {

		}
		if (mob == null || mobEntity == null) {
			return;
		}

		List<ItemStack> items = new ArrayList<ItemStack>();

		float FinalGearChance = ApplyMobLootMulti(GearChance, mob, mobEntity, world);
		float FinalCurrencyChance = ApplyMobLootMulti(CurrencyChance, mob, mobEntity, world);
		float FinalSpellChance = ApplyMobLootMulti(SpellChance, mob, mobEntity, world);

		FinalGearChance = ApplyLevelDistancePunishment(mob, player, FinalGearChance);
		FinalCurrencyChance = ApplyLevelDistancePunishment(mob, player, FinalCurrencyChance);
		FinalSpellChance = ApplyLevelDistancePunishment(mob, player, FinalSpellChance);

		int GearDrops = WhileRoll(FinalGearChance);
		int CurrencyDrops = WhileRoll(FinalCurrencyChance);
		int SpellDrops = WhileRoll(FinalSpellChance);

		GearBlueprint gearPrint = new GearBlueprint(mob.getLevel());

		SpellBlueprint spellPrint = new SpellBlueprint(mob.getLevel());

		for (int i = 0; i < GearDrops; i++) {
			items.add(RandomDamagedGear(GearGen.CreateStack(gearPrint)));
		}
		for (int i = 0; i < SpellDrops; i++) {
			items.add(SpellItemGen.Create(spellPrint));
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

	static final int LEVEL_DISTANCE_PUNISHMENT_ACTIVATION = 4;

	// prevents lvl 50 players farming lvl 1 mobs
	public static float ApplyLevelDistancePunishment(UnitData mob, UnitData player, float chance) {

		if (player.getLevel() > mob.getLevel() + LEVEL_DISTANCE_PUNISHMENT_ACTIVATION) {

			float levelDiff = mob.getLevel() / player.getLevel();

			if (levelDiff < 0.2F) {
				levelDiff = 0.2F;
			}

			return chance * levelDiff;

		}

		return chance;

	}

	public static ItemStack RandomDamagedGear(ItemStack stack) {
		if (stack.getMaxDamage() > 0) {
			float damage = (float) RandomUtils.RandomRange(75, 95) / (float) 100;
			stack.setItemDamage((int) (damage * stack.getMaxDamage()));
		}

		return stack;
	}

	public static float ApplyMobLootMulti(float chance, UnitData mob, EntityLivingBase entity, IWorldData world) {

		float first = chance;

		float after_rarity = first * Rarities.Mobs.get(mob.getRarity()).LootMultiplier();

		float after_mob_health = after_rarity * (1 + entity.getMaxHealth() / 20);

		float after_world = after_mob_health;

		if (world.isMapWorld()) {
			after_world = after_mob_health * 1 + world.getMap().getBonusLootAmount() / 100;
		}
		if (entity instanceof EntitySlime) {
			after_world /= 15;
		}

		return after_world;
	}

	public static int WhileRoll(float chance) {
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
