package com.robertx22.mine_and_slash.database.gearitemslots.bases.armor;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public abstract class BaseBoots extends BaseArmor {

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
                .equals(EquipmentSlotType.FEET);
    }

}
