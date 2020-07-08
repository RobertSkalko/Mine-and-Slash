package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherPantsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class LeatherPants extends BasePants {
    public static GearItemSlot INSTANCE = new LeatherPants();

    private LeatherPants() {

    }

    @Override
    public StatReq getRequirements() {
        return leatherArmorReq;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return leatherPrimary();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.THIEF;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return LeatherPantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Pants";
    }

    @Override
    public String GUID() {
        return "leather_pants";
    }
}
