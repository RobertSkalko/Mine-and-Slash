package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateBootsItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Boots extends BaseArmor {

    @Override
    public String GUID() {
        return "Boots";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
                .equals(EquipmentSlotType.FEET);
    }

    @Override
    public Item DefaultItem() {
        return PlateBootsItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return PlateBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Boots";
    }
}
