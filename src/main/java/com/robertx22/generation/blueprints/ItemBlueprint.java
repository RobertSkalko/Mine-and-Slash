package com.robertx22.generation.blueprints;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.RarityGen;
import com.robertx22.mmorpg.ModConfig;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class ItemBlueprint {

	public ItemBlueprint(int level) {

		if (level > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
			level = ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
		}
		this.level = level;

	}

	public int MagicFind = 0;

	public int rarity;
	public boolean RandomRarity = true;
	public int level;
	public boolean LevelRange = true;
	public int LevelVariance = 3;

	public int minRarity = -1;
	public int minLevel = 1;

	public void SetSpecificRarity(int i) {

		rarity = i;
		RandomRarity = false;

		try {
			Rarities.Items.get(i);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public int GetRarity() {

		if (RandomRarity) {

			if (minRarity > -1) {
				ItemRarity rar = Rarities.Items.get(RarityGen.Random(0).Rank());

				while (rar.Rank() < minRarity) {
					rar = Rarities.Items.get(RarityGen.Random(0).Rank());
				}
				return rar.Rank();

			} else {
				return RarityGen.Random(0).Rank();
			}
		} else {
			return rarity;
		}

	}

	public int GetLevel() {

		if (LevelRange) {
			int lvl = RandomUtils.RandomRange(level - LevelVariance, level + LevelVariance);

			if (lvl < this.minLevel) {
				lvl = this.minLevel;
			}
			if (lvl > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
				lvl = ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
			}

			return lvl;

		} else {

			return level;
		}

	}
}
