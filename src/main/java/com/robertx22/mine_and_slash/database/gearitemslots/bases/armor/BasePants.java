package com.robertx22.mine_and_slash.database.gearitemslots.bases.armor;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniquePantsItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public abstract class BasePants extends BaseArmor {

    @Override
    public String resourceID() {
        return "pants";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
            .equals(EquipmentSlotType.LEGS);

    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniquePantsItem();
    }
}