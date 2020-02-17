package com.robertx22.mine_and_slash.database.gearitemslots.bases.armor;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueChest;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public abstract class BaseChest extends BaseArmor {

    @Override
    public String resourceID() {
        return "chest";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
            .equals(EquipmentSlotType.CHEST);
    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueChest();
    }
}
