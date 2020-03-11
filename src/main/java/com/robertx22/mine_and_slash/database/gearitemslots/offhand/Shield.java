package com.robertx22.mine_and_slash.database.gearitemslots.offhand;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueShield;
import com.robertx22.mine_and_slash.items.gearitems.offhands.NormalShield;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Shield extends BaseOffHand implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Shield();

    private Shield() {

    }

    @Override
    public String resourceID() {
        return "shield";
    }

    @Override
    public List<StatMod> getPossibleSecondaryStats() {
        return Arrays.asList(new ArmorFlat().size(StatMod.Size.HIGH));
    }

    static StatReq req = new StatReq(
        LvlPointStat.VITALITY, StatReq.Size.TINY, LvlPointStat.STAMINA, StatReq.Size.TINY);

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "shield";
    }

    @Override
    public Item getDefaultItem() {
        return NormalShield.Items.get(0);
    }

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueShield();
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof ShieldItem;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return NormalShield.Items;
    }

    @Override
    public String locNameForLangFile() {
        return "Shield";
    }
}
