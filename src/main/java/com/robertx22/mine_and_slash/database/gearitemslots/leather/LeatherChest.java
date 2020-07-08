package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseChest;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherChestItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class LeatherChest extends BaseChest {
    public static GearItemSlot INSTANCE = new LeatherChest();

    private LeatherChest() {

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
        return LeatherChestItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Chest";
    }

    @Override
    public String GUID() {
        return "leather_chest";
    }
}
