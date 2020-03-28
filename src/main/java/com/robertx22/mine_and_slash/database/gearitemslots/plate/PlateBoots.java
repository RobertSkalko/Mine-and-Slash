package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;

public class PlateBoots extends BaseBoots {
    public static GearItemSlot INSTANCE = new PlateBoots();

    private PlateBoots() {

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
        return "plate_boots";
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public Item getDefaultItem() {
        return PlateBootsItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return PlateBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Plate Boots";
    }
}
