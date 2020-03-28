package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseHelmet;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherHelmetItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class LeatherHelmet extends BaseHelmet {
    public static GearItemSlot INSTANCE = new LeatherHelmet();

    private LeatherHelmet() {

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
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(leatherArmorStats());
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return LeatherHelmetItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Helmet";
    }

    @Override
    public String GUID() {
        return "leather_helmet";
    }
}
