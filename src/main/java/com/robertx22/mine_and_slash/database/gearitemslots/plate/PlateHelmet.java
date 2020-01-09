package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseHelmet;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlateHelmetItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class PlateHelmet extends BaseHelmet {
    public static GearItemSlot INSTANCE = new PlateHelmet();

    private PlateHelmet() {

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
        return "Helmet";
    }

    @Override
    public Item DefaultItem() {
        return PlateHelmetItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return PlateHelmetItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Plate Helm";
    }
}
