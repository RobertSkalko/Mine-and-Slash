package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothPantsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothPants extends BasePants {
    public static GearItemSlot INSTANCE = new ClothPants();

    private ClothPants() {

    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return clothPrimary();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.MAGE;
    }

    @Override
    public StatReq getRequirements() {
        return clothArmorReq;
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ClothPantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Pants";
    }

    @Override
    public String GUID() {
        return "cloth_pants";
    }
}
