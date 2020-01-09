package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseChest;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateChestItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class PlateChest extends BaseChest {
    public static GearItemSlot INSTANCE = new PlateChest();

    private PlateChest() {

    }

    @Override
    public StatReq getRequirements() {
        return plateArmorReq;
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.plateArmorStats();
    }

    @Override
    public String GUID() {
        return "Chest";
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
        return "Plate Chest";
    }

}
