package com.robertx22.mmorpg.config.non_mine_items;

import java.util.Arrays;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.generation.GearGen;
import com.robertx22.generation.RunedGearGen;
import com.robertx22.generation.UniqueGearGen;
import com.robertx22.generation.blueprints.GearBlueprint;
import com.robertx22.generation.blueprints.RunedGearBlueprint;
import com.robertx22.generation.blueprints.UniqueBlueprint;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.unique_items.IUnique;
import com.robertx22.uncommon.utilityclasses.IWeighted;

import net.minecraft.item.ItemStack;

public class ConfigItem implements IWeighted {

    @Override
    public int Weight() {
        return this.dropWeight;
    }

    public transient String registryName = "";

    public enum creationTypes {
        NORMAL, RUNED, UNIQUE
    }

    // public String itemID = "modid:itemid";
    public String itemType = "Sword";

    public int dropWeight = 1000;
    public int uniqueItemWeight = 0;
    public int normalItemWeight = 80;
    public int runedItemWeight = 20;

    public int randomUniqueUpToTier = 10;

    public int minRarity = 0;
    public int maxRarity = 5;

    public boolean itemIsPlayerLevel = true;

    public int itemLevelIfDoesntUsePlayerLevel = 1;

    public int levelVariance = 0;

    public String uniqueId = "";
    public boolean uniqueIsRandom = true;

    public boolean isValid() throws Exception {

        if (uniqueIsRandom == false) {
            boolean matches = false;
            for (IUnique uniq : IUnique.getAll()) {
                if (uniq.GUID().equals(this.uniqueId)) {
                    matches = true;
                }
            }
            if (matches == false) {
                System.out.println("Unique Id doesn't exist");
                return false;
            }
        }

        boolean matchtype = false;
        for (GearItemSlot slot : GearTypes.All.values()) {
            if (slot.GUID().equals(this.itemType)) {
                matchtype = true;
            }
        }
        if (matchtype == false) {
            System.out.println("Gear Type doesn't exist");
            return false;
        }

        if (normalItemWeight < 1 && this.runedItemWeight < 1 && this.uniqueItemWeight < 1) {
            System.out.println("Weights can't all be 0");
            return false;
        }

        return true;

    }

    public ItemStack create(ItemStack stack, UnitData data) {

        switch (getCreationType()) {
            case NORMAL:
                createNormal(stack, data);
                break;
            case UNIQUE:
                createUnique(stack, data);
                break;
            case RUNED:
                createRuned(stack, data);
                break;

        }

        return stack;

    }

    private creationTypes getCreationType() {

        WeightedType result = (WeightedType) RandomUtils.WeightedRandom(Arrays.asList(new WeightedType(normalItemWeight, creationTypes.NORMAL), new WeightedType(uniqueItemWeight, creationTypes.UNIQUE), new WeightedType(runedItemWeight, creationTypes.RUNED)));

        return result.type;
    }

    private int getLevel(UnitData data) {
        return this.itemIsPlayerLevel ? data.getLevel() : this.itemLevelIfDoesntUsePlayerLevel;
    }

    private ItemStack createNormal(ItemStack stack, UnitData data) {

        GearBlueprint blueprint = new GearBlueprint(getLevel(data));
        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;
        blueprint.minRarity = this.minRarity;
        blueprint.maxRarity = this.maxRarity;

        GearItemData gear = GearGen.CreateData(blueprint);
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }

    private ItemStack createUnique(ItemStack stack, UnitData data) {

        UniqueBlueprint blueprint = new UniqueBlueprint(getLevel(data), this.uniqueId);
        blueprint.uniqueIsRandom = this.uniqueIsRandom;
        blueprint.tier = randomUniqueUpToTier;
        blueprint.map_tier = this.randomUniqueUpToTier;

        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;

        GearItemData gear = UniqueGearGen.CreateData(blueprint);
        gear.isNotFromMyMod = true;

        if (gear.uniqueGUID != null || !IUnique.ITEMS.containsKey(gear.uniqueGUID)) {
            return createNormal(stack, data);
        } else {
            Gear.Save(stack, gear);
        }

        return stack;

    }

    private ItemStack createRuned(ItemStack stack, UnitData data) {

        RunedGearBlueprint blueprint = new RunedGearBlueprint(getLevel(data));
        blueprint.SetSpecificType(this.itemType);
        blueprint.LevelRange = this.levelVariance > 0;
        blueprint.LevelVariance = this.levelVariance;
        blueprint.minRarity = this.minRarity;
        blueprint.maxRarity = this.maxRarity;

        GearItemData gear = RunedGearGen.CreateData(blueprint);
        gear.isNotFromMyMod = true;

        Gear.Save(stack, gear);

        return stack;

    }
}