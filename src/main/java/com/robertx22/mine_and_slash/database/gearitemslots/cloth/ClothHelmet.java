package com.robertx22.mine_and_slash.database.gearitemslots.cloth;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BaseHelmet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.cloth.ClothHelmetItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class ClothHelmet extends BaseHelmet {

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
        return "Cloth_Helmet";
    }
}
