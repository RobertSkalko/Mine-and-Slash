package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.database.lists.Rarities;
import com.robertx22.database.lists.Spells;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class SpellItemData implements Serializable {

	public SpellItemData() {

	}

	public static int MIN_MANA_COST_PERCENT = 50;
	public static int MAX_MANA_COST_PERCENT = 100;

	private static final long serialVersionUID = -8509997736940652748L;

	public int level = 1;
	public String spellGUID;
	public int rarity;
	public int untilCooldownOver = 0;

	public int manaCostPercent = 100;
	public int scalingEffectPercent = 100;
	public int baseEffectPercent = 100;

	public int GetManaCost() {
		return ManaCostCalc(this.GetSpell().ManaCost()) * this.manaCostPercent / 100;
	}

	private int ManaCostCalc(int i) {

		return i + (i * level / 4);
	}

	public int GetBaseValue() {
		return 1 + GetSpell().BaseDamage() * level * baseEffectPercent / 100;
	}

	public float GetScalingValue() {
		return (GetSpell().ScalingDamage().Multi * scalingEffectPercent / 100);
	}

	private int MinScaling() {
		return (int) (GetSpell().ScalingDamage().Multi * Rarities.Items.get(rarity).StatPercents().Min);
	}

	private int MaxScaling() {
		return (int) (GetSpell().ScalingDamage().Multi * Rarities.Items.get(rarity).StatPercents().Max);
	}

	private int MinBase() {
		return (int) (GetSpell().BaseDamage() * Rarities.Items.get(rarity).StatPercents().Min / 100);
	}

	private int MaxBase() {
		return (int) (GetSpell().BaseDamage() * Rarities.Items.get(rarity).StatPercents().Max / 100);
	}

	private int MinMana() {
		return ManaCostCalc(this.GetSpell().ManaCost()) * MIN_MANA_COST_PERCENT / 100;
	}

	private int MaxMana() {
		return ManaCostCalc(this.GetSpell().ManaCost()) * MAX_MANA_COST_PERCENT / 100;
	}

	public String GetScalingDesc() {

		return "Scales with: " + GetSpell().ScalingDamage().GetStat().Name() + " by : "
				+ (int) (GetScalingValue() * 100) + "%" + " (" + MinScaling() + "-" + MaxScaling() + ")";

	}

	public String GetBaseDesc() {

		return "Base value: " + this.GetBaseValue() + " (" + MinBase() + "-" + MaxBase() + ")";

	}

	public String GetManaDesc() {

		return "Mana Cost: " + this.GetManaCost() + " (" + MinMana() + "-" + MaxMana() + ")";

	}

	public int GetDamage(Unit unit) {

		BaseSpell spell = GetSpell();

		int basedmg = spell.BaseDamage() * baseEffectPercent / 100 * level;
		int scalingdmg = spell.ScalingDamage().GetValue(unit) * scalingEffectPercent / 100;

		int total = basedmg + scalingdmg;

		int finalrandom = RandomUtils.RandomRange(1 + total / 2, 2 + total + total / 2);

		return finalrandom;

	}

	public BaseSpell GetSpell() {
		return Spells.All.get(spellGUID);
	}

}
