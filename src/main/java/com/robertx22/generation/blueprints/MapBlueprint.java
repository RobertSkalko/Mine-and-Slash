package com.robertx22.generation.blueprints;

import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.generation.RarityGen;

public class MapBlueprint extends ItemBlueprint {

	public MapBlueprint(int level) {
		super(level);

	}

	@Override
	public int GetRarity() {

		if (RandomRarity) {

			if (minRarity > -1) {
				MapRarity rar = Rarities.Maps.get(RarityGen.Random(0).Rank());

				while (rar.Rank() < minRarity) {
					rar = Rarities.Maps.get(RarityGen.Random(0).Rank());
				}
				return rar.Rank();

			} else {
				return RarityGen.Random(0).Rank();
			}
		} else {
			return rarity;
		}

	}
}
