package com.robertx22.mine_and_slash.data_packs.unique_gears;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SerializableUniqueGear implements IUnique {

    List<StatMod> primaryStats;
    List<StatMod> uniqueStats;
    int tier;
    int rarity;
    int weight;
    boolean canGetSet;
    StatReq requirements;
    String guid;
    String langNameID;
    String langDescID;
    String gearType;
    ResourceLocation itemID;

    public SerializableUniqueGear(List<StatMod> primaryStats, List<StatMod> uniqueStats, int tier, GearRarity rarity, int weight, boolean canGetSet, StatReq requirements, String guid, String langNameID, String langDescID, String gearType, ResourceLocation itemID) {
        this.primaryStats = primaryStats;
        this.uniqueStats = uniqueStats;
        this.tier = tier;
        this.rarity = rarity.Rank();
        this.weight = weight;
        this.canGetSet = canGetSet;
        this.requirements = requirements;
        this.guid = guid;
        this.langNameID = langNameID.contains("item.") ? langNameID : "item." + langNameID;
        this.langDescID = langDescID.contains("item.") ? langDescID : "item." + langDescID;
        this.gearType = gearType;
        this.itemID = itemID;
    }

    @Override
    public ResourceLocation getResourceLocForItem() {
        return itemID;
    }

    @Override
    public Item getUniqueItem() {
        return ForgeRegistries.ITEMS.getValue(itemID);
    }

    @Override
    public int Tier() {
        return tier;
    }

    @Override
    public List<StatMod> uniqueStats() {
        return this.uniqueStats;
    }

    @Override
    public List<StatMod> primaryStats() {
        return this.primaryStats;
    }

    @Override
    public StatReq getRequirements() {
        return requirements;
    }

    @Override
    public String locDescForLangFile() {
        return "";
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String locDescLangFileGUID() {
        return langDescID;
    }

    @Override
    public String locNameLangFileGUID() {
        return langNameID;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return SlashRegistry.GearTypes()
            .get(gearType);
    }
}
