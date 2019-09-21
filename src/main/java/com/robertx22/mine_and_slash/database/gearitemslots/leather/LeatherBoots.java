package com.robertx22.mine_and_slash.database.gearitemslots.leather;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.leather.LeatherBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class LeatherBoots extends BaseBoots {
    public static GearItemSlot INSTANCE = new LeatherBoots();

    private LeatherBoots() {

    }

    public List<Stat> statRequirements() {
        return leatherRequirements();
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return leatherArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return LeatherBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Leather Boots";
    }

    @Override
    public String GUID() {
        return "Leather_Boots";
    }
}
