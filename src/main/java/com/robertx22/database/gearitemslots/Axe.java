package com.robertx22.database.gearitemslots;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.AxeWeaponMechanic;
import com.robertx22.customitems.gearitems.weapons.ItemAxe;
import com.robertx22.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.database.stat_mods.percent.LifeOnHitPercent;
import com.robertx22.database.stat_mods.percent.LifestealPercent;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class Axe extends BaseWeapon {

    @Override
    public String GUID() {
	return "Axe";
    }

    @Override
    public List<StatMod> slotTypeStats() {
	return Arrays.asList(new LifestealPercent(), new LifeOnHitPercent());
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

}