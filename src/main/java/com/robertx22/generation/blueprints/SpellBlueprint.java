package com.robertx22.generation.blueprints;

import java.util.List;

import com.robertx22.database.lists.GearTypes;
import com.robertx22.database.lists.Spells;
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

	public BaseSpell GetSpell() {

		if (RandomSpell) {
			List<IWeighted> slots = ListUtils.CollectionToList(Spells.All.values());

			return (BaseSpell) RandomUtils.WeightedRandom(slots);

		} else {

			return Spells.All.get(spellName);
		}

	}

}
