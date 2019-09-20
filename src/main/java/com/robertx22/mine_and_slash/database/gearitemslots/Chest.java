package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseArmor;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateChestItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Chest extends BaseArmor {

    public Chest() {
    }

    @Override
    public String GUID() {
        return "Chest";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ArmorItem && ((ArmorItem) item).getEquipmentSlot()
                .equals(EquipmentSlotType.CHEST);
    }

    @Override
    public Item DefaultItem() {
        return PlateChestItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return PlateChestItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Chest";
    }

}
