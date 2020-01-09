package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public class UniqueGearBlueprint extends GearBlueprint {

    public UniqueGearBlueprint(int level, int mapTier) {
        super(level);
        this.mapTier = mapTier;
        this.tier = mapTier;

        this.rarity.setSpecificRarity(IRarity.Unique);
    }

    public UniqueGearBlueprint(int level, int mapTier, boolean tierIsRandom) {
        super(level);
        this.tierIsRandom = tierIsRandom;
        this.mapTier = mapTier;
        this.tier = mapTier;

        this.rarity.setSpecificRarity(IRarity.Unique);
    }

    public UniqueGearBlueprint(int level, String guid) {
        super(level);
        this.guid = guid;
        this.uniqueIsRandom = false;

        this.rarity.setSpecificRarity(IRarity.Unique);
    }

    private String guid = "";
    public boolean uniqueIsRandom = true;

    public int mapTier = 0;
    public int tier = -1;

    private boolean tierIsRandom = true;

    public void setSpecificID(String id) {

        this.guid = id;
        this.uniqueIsRandom = false;

    }

    public IUnique getUnique() {
        if (this.uniqueIsRandom) {

            if (this.tierIsRandom == false) {

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
                .ofSpecificGearType(gearItemSlot.get().GUID())
                .random();

    }

}
