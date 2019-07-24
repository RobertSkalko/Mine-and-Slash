package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class SpellBlueprint extends ItemBlueprint {

    public SpellBlueprint(int level) {
        super(level);
    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {

        return Rarities.Spells;

    }

    public BaseSpell GetSpell() {

        if (randomGUID) {

            return RandomUtils.weightedRandom(SlashRegistry.Spells().getAll().values());

        } else {

            return SlashRegistry.Spells().get(GUID);
        }

    }

}
