package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import net.minecraft.inventory.EquipmentSlotType;

public abstract class BaseWeapon extends GearItemSlot {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.MAINHAND;
    }

    @Override
    public SlotFamily family() {
        return SlotFamily.Weapon;
    }

}
