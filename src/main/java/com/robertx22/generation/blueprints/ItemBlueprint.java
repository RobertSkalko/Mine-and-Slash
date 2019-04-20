package com.robertx22.generation.blueprints;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.RarityGen;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.uncommon.utilityclasses.ListUtils;
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
    public int maxRarity = 5;

    public int minLevel = 1;

    public void SetSpecificRarity(int i) {

	rarity = i;
	RandomRarity = false;

    }

    public int GetRarity() {

	if (RandomRarity) {

	    if (minRarity > -1 || maxRarity < 5) {
		ItemRarity rar = Rarities.Items
			.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Items)).Rank());

		while (rar.Rank() < minRarity || rar.Rank() > maxRarity) {
		    rar = Rarities.Items.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Items)).Rank());
		}
		return rar.Rank();

	    } else {
		return RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Items)).Rank();
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
	    if (level < this.minLevel) {
		level = this.minLevel;
	    }
	    if (level > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
		level = ModConfig.Server.MAXIMUM_PLAYER_LEVEL;
	    }

	    return level;
	}

    }
}
