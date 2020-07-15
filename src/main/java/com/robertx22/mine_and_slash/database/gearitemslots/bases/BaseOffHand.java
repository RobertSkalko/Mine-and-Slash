package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import net.minecraft.inventory.EquipmentSlotType;

public abstract class BaseOffHand extends BaseGearType {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.OFFHAND;
    }

    @Override
    public final int Weight() {
        return super.Weight() / 2;
    }

}
