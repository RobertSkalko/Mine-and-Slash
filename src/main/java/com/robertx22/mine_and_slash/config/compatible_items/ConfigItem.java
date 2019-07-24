package com.robertx22.mine_and_slash.config.compatible_items;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.GearLootGen;
import com.robertx22.mine_and_slash.loot.gens.RunedGearLootGen;
import com.robertx22.mine_and_slash.loot.gens.UniqueGearLootGen;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

public class ConfigItem implements IWeighted, ISlashRegistryEntry {

    @Override
    public int Weight() {
        return this.dropWeight;
    }

    public transient String registryName = "";

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.COMPATIBLE_ITEM;
    }

    @Override
    public String GUID() {
        return this.registryName;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    public enum creationTypes {
        NORMAL,
        RUNED,
        UNIQUE
    }

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

    public ConfigItem setUniqueId(IUnique uniq) {
        this.uniqueId = uniq.GUID();
        this.uniqueIsRandom = false;
        return this;
    }

    public ConfigItem setMaxUniqueTier(int tier) {
        this.randomUniqueUpToTier = tier;
        return this;
    }

    public ConfigItem setGenerationWeights(int normalItemWeight, int runedItemWeight,
                                           int uniqueItemWeight) {
        this.normalItemWeight = normalItemWeight;
        this.uniqueItemWeight = uniqueItemWeight;
        this.runedItemWeight = runedItemWeight;
        return this;
    }

    public ConfigItem setAlwaysNormal() {
        this.normalItemWeight = 1;
        this.uniqueItemWeight = 0;
        this.runedItemWeight = 0;
        return this;
    }

    public ConfigItem setAlwaysRuned() {
        this.normalItemWeight = 0;
        this.uniqueItemWeight = 0;
        this.runedItemWeight = 1;
        return this;
    }

    public ConfigItem setAlwaysUnique() {
        this.normalItemWeight = 0;
        this.uniqueItemWeight = 1;
        this.runedItemWeight = 0;
        return this;
    }

    public ConfigItem setType(GearItemSlot type) {
        this.itemType = type.GUID();
        return this;
    }

    public ConfigItem setType(String type) {
        this.itemType = type;
        return this;
    }

    public ConfigItem setDropWeight(int weight) {
        this.dropWeight = weight;
        return this;
    }

    public ConfigItem setMinRarity(int rar) {
        this.minRarity = rar;
        return this;
    }

    public ConfigItem setSalvagable(boolean bool) {
        this.isSalvagable = bool;
        return this;
    }

    public ConfigItem setMaxRarity(int rar) {
        this.maxRarity = rar;
        return this;
    }

    public boolean isValid() throws Exception {

        if (uniqueIsRandom == false) {
            if (SlashRegistry.UniqueGears().isRegistered(uniqueId) == false) {
                throw new Exception("Unique Id doesn't exist: " + this.uniqueId);
            }
        }

        boolean matchtype = false;
        for (GearItemSlot slot : SlashRegistry.GearTypes().getAll().values()) {
            if (slot.GUID().equals(this.itemType)) {
                matchtype = true;
            }
        }
        if (matchtype == false) {
            throw new Exception("Gear Type doesn't exist: " + this.itemType);
        }
        if (normalItemWeight < 1 && this.runedItemWeight < 1 && this.uniqueItemWeight < 1) {
            throw new Exception("Weights can't all be 0 on an item: ");
        }

        return true;

    }

    public ItemStack create(ItemStack stack, int level) {

        level = this.getLevel(level);

        switch (getCreationType()) {
            case NORMAL:
                createNormal(stack, level);
                break;
            case UNIQUE:
                createUnique(stack, level);
                break;
            case RUNED:
                createRuned(stack, level);
                break;

        }

        return stack;

    }

    private creationTypes getCreationType() {

        WeightedType result = RandomUtils.weightedRandom(Arrays.asList(new WeightedType(normalItemWeight, creationTypes.NORMAL), new WeightedType(uniqueItemWeight, creationTypes.UNIQUE), new WeightedType(runedItemWeight, creationTypes.RUNED)));

        return result.type;
    }

    private int getLevel(int playerlevel) {
        return MathHelper.clamp(playerlevel, minLevel, maxLevel);
    }

    private ItemStack createNormal(ItemStack stack, int level) {

        GearBlueprint blueprint = new GearBlueprint(level);
        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;
        blueprint.minRarity = this.minRarity;
        blueprint.maxRarity = this.maxRarity;

        GearItemData gear = GearLootGen.CreateData(blueprint);
        gear.isSalvagable = this.isSalvagable;
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }

    private ItemStack createUnique(ItemStack stack, int level) {

        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(level, this.uniqueId);
        blueprint.uniqueIsRandom = this.uniqueIsRandom;
        blueprint.tier = randomUniqueUpToTier;
        blueprint.map_tier = this.randomUniqueUpToTier;

        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;

        GearItemData gear = UniqueGearLootGen.CreateData(blueprint);
        gear.isSalvagable = this.isSalvagable;
        gear.isNotFromMyMod = true;

        if (gear.uniqueGUID == null || !SlashRegistry.UniqueGears()
                .isRegistered(gear.uniqueGUID)) {
            return createNormal(stack, level);
        } else {
            Gear.Save(stack, gear);
        }

        return stack;

    }

    private ItemStack createRuned(ItemStack stack, int level) {

        RunedGearBlueprint blueprint = new RunedGearBlueprint(level);
        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;
        blueprint.minRarity = this.minRarity;
        blueprint.maxRarity = this.maxRarity;

        GearItemData gear = RunedGearLootGen.CreateData(blueprint);
        gear.isSalvagable = this.isSalvagable;
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }
}
