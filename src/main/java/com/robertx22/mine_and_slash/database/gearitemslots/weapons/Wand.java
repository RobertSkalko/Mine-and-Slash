package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.unique_items.bases.BaseUniqueWand;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.WandWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemWand;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Wand extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Wand();

    private Wand() {

    }

    @Override
    public String resourceID() {
        return "wand";
    }

    @Override
    public int cooldownTicks() {
        return 40;
    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.MEDIUM);

    @Override
    public Item getBaseUniqueItem() {
        return new BaseUniqueWand();
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "wand";
    }

    @Override
    public Item getDefaultItem() {
        return ItemWand.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemWand.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new WandWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Staff";
    }
}