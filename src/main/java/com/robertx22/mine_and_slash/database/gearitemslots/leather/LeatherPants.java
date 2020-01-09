package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
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
    public List<StatMod> PossibleSecondaryStats() {
        return this.leatherArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherPantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Pants";
    }

    @Override
    public String GUID() {
        return "Leather_Pants";
    }
}
