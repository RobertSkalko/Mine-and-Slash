package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.CrossBowWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemCrossbow;
import net.minecraft.item.Item;

import java.util.HashMap;

public class CrossBow extends BaseWeapon {

    @Override
    public String GUID() {
        return "Crossbow";
    }

    @Override
    public Item DefaultItem() {
        return ItemCrossbow.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemCrossbow.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new CrossBowWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Hammer";
    }
}
