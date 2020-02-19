package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlatePantsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class PlatePants extends BasePants {
    public static GearItemSlot INSTANCE = new PlatePants();

    private PlatePants() {

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
        return "plate_pants";
    }

    @Override
    public Item getDefaultItem() {
        return PlatePantsItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return PlatePantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Plate Greaves";
    }
}
