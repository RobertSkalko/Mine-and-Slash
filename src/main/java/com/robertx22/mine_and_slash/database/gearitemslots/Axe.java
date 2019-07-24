package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.AxeWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemAxe;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Axe extends BaseWeapon {

    @Override
    public String GUID() {
        return "Axe";
    }

    @Override
    public Item DefaultItem() {
        return ItemAxe.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemAxe.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new AxeWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Axe";
    }
}