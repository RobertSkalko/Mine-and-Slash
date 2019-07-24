package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.BowWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemBow;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Bow extends BaseWeapon {

    @Override
    public String GUID() {
        return "Bow";
    }

    @Override
    public Item DefaultItem() {
        return ItemBow.Items.get(0);
    }

    @Override
    public String locNameForLangFile() {
        return "Bow";
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemBow.Items;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new BowWeaponMechanic();
    }
}
