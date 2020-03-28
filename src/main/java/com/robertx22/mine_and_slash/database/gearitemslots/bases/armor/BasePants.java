package com.robertx22.mine_and_slash.database.gearitemslots.bases.armor;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class BasePants extends BaseArmor {
    @Override
    public EquipmentSlotType getVanillaSlotType() {
        return EquipmentSlotType.LEGS;
    }

    @Override
    public String resourceID() {
        return "pants";
    }

}