package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.loot.blueprints.bases.LevelPart;
import com.robertx22.mine_and_slash.loot.blueprints.bases.RarityPart;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

// use once and discard!! unsure how to regulate this
public abstract class ItemBlueprint {

    public ItemBlueprint(int level) {
        this.level.startPointLvl = level;
    }

    public RarityPart rarity = new RarityPart(getRarityContainer());
    public LevelPart level = new LevelPart();

    public String GUID = "";
    public boolean randomGUID = true;

    public void SetSpecificType(String type) {

        GUID = type;
        randomGUID = false;

    }

    public abstract RaritiesContainer<? extends Rarity> getRarityContainer();

}
