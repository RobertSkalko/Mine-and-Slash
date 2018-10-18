package com.robertx22.generation.blueprints;

import com.robertx22.database.lists.Rarities;
import com.robertx22.gearitem.ItemRarity;
import com.robertx22.generation.RarityGen;
import com.robertx22.utilityclasses.RandomUtils;

public class ItemBlueprint {

	public ItemBlueprint(int level) {
		this.level = level;
	}

	public int MagicFind = 0;

	public int rarity;
	public boolean RandomRarity = true;
	public int level;
	public boolean LevelRange = true;
	public int LevelVariance = 5;

	public void SetSpecificRarity(int i) {

		rarity = i;
		RandomRarity = false;

		try {
			Rarities.Items.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public ItemRarity GetRarity() {

		if (RandomRarity) {
			return Rarities.Items.get(RarityGen.Random(0).Rank());

		} else {
			return Rarities.Items.get(rarity);
		}

	}

	public int GetLevel() {

		if (LevelRange) {
			int lvl = RandomUtils.RandomRange(level - LevelVariance, level + LevelVariance);

			if (lvl < 1) {
				lvl = 1;
			}

			return lvl;

		} else {
			return level;
		}

	}
}
