package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.database.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
import com.robertx22.items.gearitems.weapons.ItemStaff;

import net.minecraft.item.Item;

public class Staff extends BaseWeapon {

    @Override
    public String GUID() {
	return "Staff";
    }

    @Override
    public List<StatMod> slotTypeStats() {
	return Arrays.asList(new ManaOnHitFlat());

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
}