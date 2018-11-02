package com.robertx22.generation;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.generation.blueprints.SpellBlueprint;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.datasaving.SpellSaving;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import net.minecraft.item.ItemStack;

public class SpellItemGen {

	public static ItemStack Create(SpellBlueprint blueprint) {

		BaseSpell spell = blueprint.GetSpell();
		ItemStack stack = new ItemStack(spell.SpellItem());
		SpellItemData data = new SpellItemData();
		ItemRarity rarity = blueprint.GetRarity();

		data.rarity = rarity.Rank();
		data.spellGUID = spell.GUID();

		data.level = blueprint.GetLevel();
		data.baseEffectPercent = Random(rarity);
		data.scalingEffectPercent = Random(rarity);
		data.manaCostPercent = RandomUtils.RandomRange(SpellItemData.MIN_MANA_COST_PERCENT,
				SpellItemData.MAX_MANA_COST_PERCENT);

		stack.setStackDisplayName(rarity.Color() + rarity.Name() + " " + data.GetSpell().Name());

		SpellSaving.Save(stack, data);

		return stack;

	}

	private static int Random(ItemRarity rarity) {

		return RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);

	}
}
