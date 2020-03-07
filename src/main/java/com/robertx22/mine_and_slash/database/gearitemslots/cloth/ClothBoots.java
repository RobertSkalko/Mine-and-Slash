package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothBoots extends BaseBoots {

    public static GearItemSlot INSTANCE = new ClothBoots();

    private ClothBoots() {

    }

    @Override
    public StatReq getRequirements() {
        return clothArmorReq;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return clothPrimary();
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ClothBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Slippers";
    }

    @Override
    public String GUID() {
        return "cloth_boots";
    }
}
