package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Spells;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class SpellItemData implements Serializable {

	public SpellItemData() {

	}

	public static int MIN_MANA_COST_PERCENT = 50;
	public static int MAX_MANA_COST_PERCENT = 100;

	private static final long serialVersionUID = -8509997736940652748L;

	@Store
	public int level = 1;
	@Store
	public String spellGUID;
	@Store
	public int rarity;
	@Store
	public int untilCooldownOver = 0;
	@Store
	public int manaCostPercent = 100;
	@Store
	public int scalingEffectPercent = 100;
	@Store
	public int baseEffectPercent = 100;

	public int GetManaCost() {
		return this.GetSpell().ManaCost() * this.manaCostPercent / 100;
	}

	public int GetBaseValue() {
		return 1 + GetSpell().BaseValue() * level * baseEffectPercent / 100;
	}

	public float GetScalingValue() {
		return (GetSpell().ScalingValue().Multi * scalingEffectPercent / 100);
	}

	private int MinScaling() {
		return (int) (GetSpell().ScalingValue().Multi * Rarities.Items.get(rarity).SpellScalingPercents().Min);
	}

	private int MaxScaling() {
		return (int) (GetSpell().ScalingValue().Multi * Rarities.Items.get(rarity).SpellScalingPercents().Max);
	}

	private int MinBase() {
		return (int) (1 + GetSpell().BaseValue() * level * Rarities.Items.get(rarity).SpellBasePercents().Min / 100);
	}

	private int MaxBase() {
		return (int) (1 + GetSpell().BaseValue() * level * Rarities.Items.get(rarity).SpellBasePercents().Max / 100);
	}

	private int MinMana() {
		return this.GetSpell().ManaCost() * MIN_MANA_COST_PERCENT / 100;
	}

	private int MaxMana() {
		return this.GetSpell().ManaCost() * MAX_MANA_COST_PERCENT / 100;
	}

	public String GetScalingDesc() {

		return "Scales with: " + GetSpell().ScalingValue().GetStat().Name() + " by : " + (int) (GetScalingValue() * 100)
				+ "%" + " (" + MinScaling() + "-" + MaxScaling() + ")";

	}

	public String GetBaseDesc() {

		return "Base value: " + this.GetBaseValue() + " (" + MinBase() + "-" + MaxBase() + ")";

	}

	public String GetManaDesc() {

		return "Mana Cost: " + this.GetManaCost() + " (" + MinMana() + "-" + MaxMana() + ")";

	}

	public int GetDamage(Unit unit) {

		BaseSpell spell = GetSpell();

		int basedmg = spell.BaseValue() * baseEffectPercent / 100 * level;
		int scalingdmg = spell.ScalingValue().GetValue(unit) * scalingEffectPercent / 100;

		int total = basedmg + scalingdmg;

		int finalrandom = RandomUtils.RandomRange(1 + total / 2, 2 + total + total / 2);

		return finalrandom;

	}

	public BaseSpell GetSpell() {
		return Spells.All.get(spellGUID);
	}

}
