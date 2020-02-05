package com.robertx22.mine_and_slash.database.gearitemslots.offhand;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.items.gearitems.offhands.MyTorch;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Torch extends BaseOffHand implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Torch();

    private Torch() {

    }

    @Override
    public String resourceID() {
        return "torch";
    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

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

    float multi = 2F;

    @Override
    public List<PosStats> PrimaryStats() {
        return Arrays.asList(new PosStats(new ManaRegenFlat().multi(multi)),
                             new PosStats(new EnergyRegenFlat().multi(multi)),
                             new PosStats(new MagicShieldRegenFlat().multi(multi))
        );
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
