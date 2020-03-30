package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseChest;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
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
    public PlayStyle getPlayStyle() {
        return PlayStyle.MAGE;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return clothPrimary();
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(clothArmorSecondary());
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ClothChestItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Vest";
    }

    @Override
    public String GUID() {
        return "cloth_chest";
    }
}
