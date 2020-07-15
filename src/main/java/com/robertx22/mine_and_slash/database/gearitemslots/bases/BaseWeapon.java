package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import net.minecraft.inventory.EquipmentSlotType;

public abstract class BaseWeapon extends BaseGearType {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.MAINHAND;
    }

}
