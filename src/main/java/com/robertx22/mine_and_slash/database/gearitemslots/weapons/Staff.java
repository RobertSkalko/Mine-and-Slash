package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemStaff;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Staff extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Staff();

    private Staff() {

    }

    @Override
    public String resourceID() {
        return "staff";
    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int primaryStatsAmount() {
        return 2;
    }

    @Override
    public String GUID() {
        return "Staff";
    }

    @Override
    public Item DefaultItem() {
        return ItemStaff.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemStaff.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new StaffWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Staff";
    }
}