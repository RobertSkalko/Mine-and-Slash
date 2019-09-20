package com.robertx22.mine_and_slash.items.gearitems.bases;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IDyeableArmorItem;

public abstract class BaseDyableArmorItem extends BaseArmorItem implements IDyeableArmorItem {

    public BaseDyableArmorItem(Type type, int rarity, EquipmentSlotType slot) {
        super(type, rarity, slot);
    }
}
