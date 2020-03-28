package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseChest;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateChestItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class PlateChest extends BaseChest {
    public static GearItemSlot INSTANCE = new PlateChest();

    private PlateChest() {

    }

    @Override
    public StatReq getRequirements() {
        return plateArmorReq;
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(plateArmorStats());
    }

    @Override
    public String GUID() {
        return "plate_chest";
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public Item getDefaultItem() {
        return PlateChestItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return PlateChestItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Plate Chest";
    }

}
