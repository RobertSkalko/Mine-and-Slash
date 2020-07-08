package com.robertx22.mine_and_slash.data_generation.gear_slots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class SerializedGearSlot extends GearItemSlot {

    PlayStyle playStyle;
    EquipmentSlotType vanillaSlot;
    public String resourceID;
    GearSlotType slotType;

    @Override
    public PlayStyle getPlayStyle() {
        return playStyle;
    }

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return vanillaSlot;
    }

    @Override
    public String resourceID() {
        return resourceID;
    }

    @Override
    public GearSlotType slotType() {
        return slotType;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return null;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return null;
    }

    @Override
    public StatReq getRequirements() {
        return null;
    }

    @Override
    public String locNameForLangFile() {
        return null;
    }

    @Override
    public String GUID() {
        return null;
    }
}
