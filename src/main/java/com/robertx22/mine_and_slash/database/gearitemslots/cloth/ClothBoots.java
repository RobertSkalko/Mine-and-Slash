package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseBoots;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothBootsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothBoots extends BaseBoots {

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ClothBootsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Slippers";
    }

    @Override
    public String GUID() {
        return "Cloth_Boots";
    }
}
