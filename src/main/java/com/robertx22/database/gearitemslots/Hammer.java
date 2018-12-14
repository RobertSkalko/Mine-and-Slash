package com.robertx22.database.gearitemslots;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.HammerWeaponMechanic;
import com.robertx22.customitems.gearitems.weapons.ItemHammer;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;

import net.minecraft.item.Item;

public class Hammer extends BaseWeapon {

    @Override
    public String GUID() {
	return "Hammer";
    }

    @Override
    public Item DefaultItem() {
	return ItemHammer.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
	return ItemHammer.Items;
    }

    @Override
    public int Weight() {
	return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
	return new HammerWeaponMechanic();
    }
}
