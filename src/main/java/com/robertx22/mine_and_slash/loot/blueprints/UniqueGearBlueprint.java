package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

public class UniqueGearBlueprint extends GearBlueprint {

    public UniqueGearBlueprint(int level, int map_tier, boolean randomTier) {
        super(level);
        this.randomTier = randomTier;
        this.map_tier = map_tier;
        this.tier = map_tier;

    }

    public UniqueGearBlueprint(int level, String guid) {
        super(level);
        this.guid = guid;
        this.uniqueIsRandom = false;

    }

    @Override
    public int getRarity() {
        return new UniqueItem().Rank();
    }

    private String guid = "";
    public boolean uniqueIsRandom = true;

    public int map_tier = 0;
    public int tier = -1;

    private boolean randomTier = true;

    public void setSpecificID(String id) {

        this.guid = id;
        this.uniqueIsRandom = false;

    }

    public IUnique getUnique() {
        if (this.uniqueIsRandom) {

            if (this.randomTier == false) {

                return SlashRegistry.UniqueGears()
                        .getWrapped()
                        .ofExactTier(tier)
                        .random();

            } else {
                return randomUnique();
            }
        } else {
            return SlashRegistry.UniqueGears().get(this.guid);
        }

    }

    private IUnique randomUnique() {

        return SlashRegistry.UniqueGears()
                .getWrapped()
                .ofTierOrLess(tier)
                .ofSpecificGearType(gearType)
                .random();

    }

}
