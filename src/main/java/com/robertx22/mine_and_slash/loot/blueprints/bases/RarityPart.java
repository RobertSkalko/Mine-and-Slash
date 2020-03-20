package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.rarities.BaseRaritiesContainer;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RarityPart extends BlueprintPart<Rarity> {

    BaseRaritiesContainer container;

    public int minRarity = -1;
    public int maxRarity = 5;
    public float chanceForHigherRarity = 0;

    public void setChanceForHigherRarityBasedOnMapTier() {
        int tier = this.blueprint.tier.get();

        this.chanceForHigherRarity = 2 * tier;

        if (tier > 5) {
            this.chanceForHigherRarity += 5;
        } else if (tier > 10) {
            this.chanceForHigherRarity += 10;
        } else if (tier > 15) {
            this.chanceForHigherRarity += 15;
        } else if (tier > 18) {
            this.chanceForHigherRarity += 25;
        }

    }

    public RarityPart(ItemBlueprint blueprint) {
        super(blueprint);
        this.container = blueprint.getRarityContainer();
    }

    @Override
    protected Rarity generateIfNull() {

        List<Rarity> possible = (List<Rarity>) container.getAllRarities()
            .stream()
            .filter(x -> {
                int r = ((Rarity) x).Rank();
                return r >= minRarity && r <= maxRarity;
            })
            .collect(Collectors.toList());

        Rarity rar = RandomUtils.weightedRandom(possible);

        if (rar.Rank() < IRarity.Legendary && RandomUtils.roll(chanceForHigherRarity)) {
            rar = container.get(rar.Rank() + 1);
        }

        return rar;
    }

    public void setSpecificRarity(int rank) {
        this.set(container.get(rank));
    }

}


