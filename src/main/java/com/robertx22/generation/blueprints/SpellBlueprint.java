package com.robertx22.generation.blueprints;

import java.util.List;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.Spells;
import com.robertx22.generation.RarityGen;
import com.robertx22.spells.bases.BaseSpell;
import com.robertx22.uncommon.utilityclasses.IWeighted;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

public class SpellBlueprint extends ItemBlueprint {

    public SpellBlueprint(int level) {
	super(level);
    }

    public String spellName;
    public boolean RandomSpell = true;

    public void SetSpecificType(String i) {

	spellName = i;
	RandomSpell = false;

	try {
	    GearTypes.All.get(i);
	} catch (IndexOutOfBoundsException e) {
	    e.printStackTrace();
	}

    }

    public int GetRarity() {

	if (RandomRarity) {

	    if (minRarity > -1) {
		ItemRarity rar = Rarities.Items
			.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Spells)).Rank());

		while (rar.Rank() < minRarity) {
		    rar = Rarities.Items.get(RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Spells)).Rank());
		}
		return rar.Rank();

	    } else {
		return RarityGen.Random(0, ListUtils.CollectionToList(Rarities.Spells)).Rank();
	    }
	} else {
	    return rarity;
	}

    }

    public BaseSpell GetSpell() {

	if (RandomSpell) {
	    List<IWeighted> slots = ListUtils.CollectionToList(Spells.All.values());

	    return (BaseSpell) RandomUtils.WeightedRandom(slots);

	} else {

	    return Spells.All.get(spellName);
	}

    }

}
