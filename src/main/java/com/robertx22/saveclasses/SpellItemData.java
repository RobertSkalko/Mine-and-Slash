package com.robertx22.saveclasses;

import java.io.Serializable;

import com.robertx22.database.lists.Spells;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.utilityclasses.RandomUtils;

public class SpellItemData implements Serializable {

	private static final long serialVersionUID = -8509997736940652748L;

	public int level;
	public String spellGUID;
	public int rarity;
	public int untilCooldownOver;

	public int manaCostPercent;
	public int scalingEffectPercent;
	public int baseEffectPercent;
	public int cooldownPercent;

	public int GetManaCost() {
		return GetSpell().ManaCost() * manaCostPercent / 100;
	}

	public int GetDamage(Unit unit) {

		BaseSpell spell = GetSpell();

		int basedmg = spell.BaseDamage() * baseEffectPercent / 100;
		int scalingdmg = spell.ScalingDamage().GetValue(unit) * scalingEffectPercent / 100;

		int total = basedmg + scalingdmg;

		int finalrandom = RandomUtils.RandomRange(total / 2, total + total / 2);

		return finalrandom;

	}

	public int GetCooldown() {
		return GetSpell().Cooldown() * cooldownPercent / 100;
	}

	public BaseSpell GetSpell() {
		return Spells.All.get(spellGUID);
	}

	public static SpellItemData Random(int lvl) {

		SpellItemData spell = new SpellItemData();

		return spell;
	}
}
