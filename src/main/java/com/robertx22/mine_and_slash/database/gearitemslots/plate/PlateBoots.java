package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class PlateBoots extends BaseBoots {
    public static GearItemSlot INSTANCE = new PlateBoots();

    private PlateBoots() {

    }

    @Override
    public StatReq getRequirements() {
        return plateArmorReq;
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return this.plateArmorStats();
    }

    @Override
    public String GUID() {
        return "plate_boots";
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
