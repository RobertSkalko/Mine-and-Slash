package com.robertx22.saveclasses;

import java.io.Serializable;

public class SpellItemData implements Serializable {

	private static final long serialVersionUID = -8509997736940652748L;

	public int level;
	public int manaCost;
	public String spellGUID;
	public int rarity;
	public int percent;
	public int cooldown;
	public int untilCooldownOver;

	public static SpellItemData Random(int lvl) {

		SpellItemData spell = new SpellItemData();

		return spell;
	}
}
