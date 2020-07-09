package com.robertx22.mine_and_slash.database.gearitemslots.bases;

public abstract class BaseArmor extends GearItemSlot {

    @Override
    public final int Weight() {
        return super.Weight() / 3;
    }

    @Override
    public SlotFamily slotTypeFamily() {
        return SlotFamily.Armor;
    }
}
