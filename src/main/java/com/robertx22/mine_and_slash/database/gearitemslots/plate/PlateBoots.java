package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class PlateBoots extends BaseBoots {
    public static GearItemSlot INSTANCE = new PlateBoots();

    private PlateBoots() {

    }

    @Override
    public List<Stat> statRequirements() {
        return plateRequirements();
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.plateArmorStats();
    }

    @Override
    public String GUID() {
        return "Boots";
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
        return "Plate Boots";
    }
}
