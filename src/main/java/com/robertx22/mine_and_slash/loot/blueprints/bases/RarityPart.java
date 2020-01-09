package com.robertx22.mine_and_slash.loot.blueprints.bases;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.loot.blueprints.ItemBlueprint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

public class RarityPart extends BlueprintPart<Rarity> {

    RaritiesContainer container;

    public int minRarity = -1;
    public int maxRarity = 5;

    public RarityPart(ItemBlueprint blueprint) {
        super(blueprint);
        this.container = blueprint.getRarityContainer();
    }

    @Override
    protected Rarity generateIfNull() {

        Rarity rar = container.random();

        while (rar.Rank() < minRarity || rar.Rank() > maxRarity) {
            rar = container.random();
        }

        return rar;
    }

    public void setSpecificRarity(int rank) {
        this.set(container.get(rank));
    }

}


