package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import net.minecraft.inventory.EquipmentSlotType;

public abstract class BaseCurio extends GearItemSlot {

    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return null;
    }

    @Override
    public int Weight() {
        return 750;
    }

}
