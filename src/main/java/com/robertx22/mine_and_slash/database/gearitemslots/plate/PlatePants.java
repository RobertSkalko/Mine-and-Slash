package com.robertx22.mine_and_slash.database.gearitemslots.plate;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.armor.BasePants;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.gearitems.armor.plate.PlatePantsItem;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;

public class PlatePants extends BasePants {

    public List<Stat> statRequirements() {
        return plateRequirements();
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.plateArmorStats();
    }

    @Override
    public String GUID() {
        return "Pants";
    }

    @Override
    public Item DefaultItem() {
        return PlatePantsItem.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return PlatePantsItem.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Plate Greaves";
    }
}
