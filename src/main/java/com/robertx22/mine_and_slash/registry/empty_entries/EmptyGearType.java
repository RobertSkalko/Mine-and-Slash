package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.util.List;

public class EmptyGearType extends GearItemSlot {

    @Override
    public List<StatModifier> ImplicitStats() {
        return null;
    }

    @Override
    public List<StatModifier> BaseStats() {
        return null;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return null;
    }

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return null;
    }

    @Override
    public SlotFamily slotTypeFamily() {
        return null;
    }

    @Override
    public List<SlotTag> getTags() {
        return null;
    }

    @Override
    public Item getItem() {
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
