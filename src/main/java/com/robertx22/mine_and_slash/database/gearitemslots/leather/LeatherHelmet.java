package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherHelmetItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class LeatherHelmet extends BaseHelmet {

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.leatherArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherHelmetItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Helmet";
    }

    @Override
    public String GUID() {
        return "Leather_Helmet";
    }
}
