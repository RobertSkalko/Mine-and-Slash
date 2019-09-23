package com.robertx22.mine_and_slash.database.gearitemslots.offhand;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

import java.util.HashMap;

public class Shield extends BaseOffHand implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Shield();

    private Shield() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.SMALL, LvlPointStat.STAMINA, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "Shield";
    }

    @Override
    public Item DefaultItem() {
        return NormalShield.Items.get(0);
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ShieldItem;
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return NormalShield.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Shield";
    }
}
