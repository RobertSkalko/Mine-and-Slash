package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseHelmet;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothHelmetItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothHelmet extends BaseHelmet {
    public static GearItemSlot INSTANCE = new ClothHelmet();

    private ClothHelmet() {

    }

    @Override
    public List<PosStats> PrimaryStats() {
        return clothPrimary();
    }

    @Override
    public StatReq getRequirements() {
        return clothArmorReq;
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ClothHelmetItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Hat";
    }

    @Override
    public String GUID() {
        return "cloth_helmet";
    }
}
