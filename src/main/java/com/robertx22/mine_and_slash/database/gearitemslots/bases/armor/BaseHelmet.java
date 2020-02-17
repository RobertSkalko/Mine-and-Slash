package com.robertx22.mine_and_slash.database.gearitemslots.bases.armor;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueHelmet;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public abstract class BaseHelmet extends BaseArmor {

    @Override
    public String resourceID() {
        return "helmet";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
            .equals(EquipmentSlotType.HEAD);
    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueHelmet();
    }

}
