package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseChest;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothChestItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothChest extends BaseChest {

    public static GearItemSlot INSTANCE = new ClothChest();

    private ClothChest() {

    }

    @Override
    public StatReq getRequirements() {
        return clothArmorReq;
    }

    @Override
    public List<PosStats> PrimaryStats() {
        return clothPrimary();
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ClothChestItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Vest";
    }

    @Override
    public String GUID() {
        return "Cloth_Chest";
    }
}
