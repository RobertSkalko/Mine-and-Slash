package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemStaff;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Staff extends BaseWeapon {

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