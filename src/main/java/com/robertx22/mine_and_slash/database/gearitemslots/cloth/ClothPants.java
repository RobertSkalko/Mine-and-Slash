package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothPantsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothPants extends BasePants {

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.clothArmorStats();
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ClothPantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Cloth Pants";
    }

    @Override
    public String GUID() {
        return "Cloth_Pants";
    }
}
