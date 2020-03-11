package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmptyGearType extends GearItemSlot {
    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return new ArrayList<>();
    }

    @Override
    public StatReq getRequirements() {
        return noReq;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return null;
    }

    @Override
    public Item getBaseUniqueItem() {
        return null;
    }

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return null;
    }

    @Override
    public String resourceID() {
        return "";
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return new ArrayList<>();
    }

    @Override
    public Item getDefaultItem() {
        return Items.AIR;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return new HashMap<>();
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return "";
    }
}
