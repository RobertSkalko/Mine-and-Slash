package com.robertx22.mine_and_slash.config.compatible_items;

import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;

public class OldConfigItem implements IWeighted {

    @Override
    public int Weight() {
        return this.dropWeight;
    }

    public transient String registryName = "";

    public String itemType = "Sword";

    public boolean isSalvagable = false;

    public int dropWeight = 1000;
    public int uniqueItemWeight = 0;
    public int normalItemWeight = 80;
    public int runedItemWeight = 20;

    public int randomUniqueUpToTier = 10;

    public int minRarity = 0;
    public int maxRarity = 5;

    public int levelVariance = 0;

    public String uniqueId = "";
    public boolean uniqueIsRandom = true;

    public int minLevel = 1;
    public int maxLevel = 100;

    public boolean statsAddedOnlyOnDrop = false;
    public boolean dropsAsLoot = true;

    public CompatibleItem convertToNewFormat() {
        CompatibleItem item = new CompatibleItem();

        item.guid = registryName;
        item.item_id = registryName;
        item.item_type = IGUID.getformattedString(itemType);

        item.min_rarity = minRarity;
        item.max_rarity = maxRarity;

        item.can_be_salvaged = isSalvagable;
        item.only_add_stats_if_loot_drop = statsAddedOnlyOnDrop;
        item.add_to_loot_drops = dropsAsLoot;
        item.loot_drop_weight = dropWeight;

        item.unique_id = uniqueId;
        item.if_unique_random_up_to_tier = randomUniqueUpToTier;

        item.normal_item_weight = normalItemWeight;
        item.runed_item_weight = runedItemWeight;
        item.unique_item_weight = uniqueItemWeight;

        return item;
    }

    public OldConfigItem setUniqueId(IUnique uniq) {
        this.uniqueId = uniq.GUID();
        this.uniqueIsRandom = false;
        return this;
    }

    public OldConfigItem setMaxUniqueTier(int tier) {
        this.randomUniqueUpToTier = tier;
        return this;
    }

    public OldConfigItem setGenerationWeights(int normalItemWeight, int runedItemWeight, int uniqueItemWeight) {
        this.normalItemWeight = normalItemWeight;
        this.uniqueItemWeight = uniqueItemWeight;
        this.runedItemWeight = runedItemWeight;
        return this;
    }

    public OldConfigItem setAlwaysNormal() {
        this.normalItemWeight = 1;
        this.uniqueItemWeight = 0;
        this.runedItemWeight = 0;
        return this;
    }

    public OldConfigItem setAlwaysRuned() {
        this.normalItemWeight = 0;
        this.uniqueItemWeight = 0;
        this.runedItemWeight = 1;
        return this;
    }

    public OldConfigItem setAlwaysUnique() {
        this.normalItemWeight = 0;
        this.uniqueItemWeight = 1;
        this.runedItemWeight = 0;
        return this;
    }

    public OldConfigItem setType(GearItemSlot type) {
        this.itemType = type.GUID();
        return this;
    }

    public OldConfigItem setType(String type) {
        this.itemType = type;
        return this;
    }

    public OldConfigItem setDropWeight(int weight) {
        this.dropWeight = weight;
        return this;
    }

    public OldConfigItem setMinRarity(int rar) {
        this.minRarity = rar;
        return this;
    }

    public OldConfigItem setSalvagable(boolean bool) {
        this.isSalvagable = bool;
        return this;
    }

    public OldConfigItem setMaxRarity(int rar) {
        this.maxRarity = rar;
        return this;
    }

    public OldConfigItem setstatsAddedOnlyOnDrop(boolean bool) {
        this.statsAddedOnlyOnDrop = bool;
        return this;
    }

    public OldConfigItem setdropsAsLoot(boolean bool) {
        this.dropsAsLoot = bool;
        return this;
    }

    public OldConfigItem setMinLevel(int rar) {
        this.minLevel = rar;
        return this;
    }

    public OldConfigItem setMaxLevel(int rar) {
        this.maxLevel = rar;
        return this;
    }

    public boolean isValid() throws Exception {

        if (uniqueIsRandom == false) {
            if (SlashRegistry.UniqueGears()
                .isRegistered(uniqueId) == false) {
                throw new Exception("Unique Id doesn't exist: " + this.uniqueId);
            }
        }

        boolean matchtype = false;
        for (GearItemSlot slot : SlashRegistry.GearTypes()
            .getAll()
            .values()) {
            if (slot.GUID()
                .equals(this.itemType)) {
                matchtype = true;
            }
        }
        if (matchtype == false) {
            throw new Exception("Gear type doesn't exist: " + this.itemType);
        }
        if (normalItemWeight < 1 && this.runedItemWeight < 1 && this.uniqueItemWeight < 1) {
            throw new Exception("Weights can't all be 0 on an item: ");
        }

        return true;

    }

}
