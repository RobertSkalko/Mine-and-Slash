package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.SwordWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemSword;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Sword extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Sword();

    private Sword() {

    }

    @Override
    public String resourceID() {
        return "sword";
    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.TINY);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "Sword";
    }

    @Override
    public Item DefaultItem() {
        return ItemSword.Items.get(0);
    }

    @Override
    public String locNameForLangFile() {
        return "Sword";
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemSword.Items;
    }

    @Override
    public int Weight() {
        return 1500;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new SwordWeaponMechanic();
    }
}
