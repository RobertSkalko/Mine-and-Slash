package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.items.gearitems.offhands.MyTorch;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Torch extends BaseOffHand {

    @Override
    public String GUID() {
        return "Torch";
    }

    @Override
    public Item DefaultItem() {
        return MyTorch.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return MyTorch.Items;
    }

    float multi = 2.5F;

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new ManaRegenFlat().multi(multi), new EnergyRegenFlat().multi(multi));
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return this.coreStatMods();
    }

    @Override
    public String locNameForLangFile() {
        return "Torch";
    }

}
