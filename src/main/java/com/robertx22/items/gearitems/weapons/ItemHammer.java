package com.robertx22.items.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.items.gearitems.bases.IWeapon;
import com.robertx22.items.gearitems.bases.WeaponMechanic;
import com.robertx22.items.gearitems.weapon_mechanics.HammerWeaponMechanic;

import net.minecraft.item.Item;

public class ItemHammer extends BaseWeaponItem implements IWeapon {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemHammer() {

	}

	@Override
	public String Name() {
		return "Hammer";
	}

	@Override
	public WeaponMechanic mechanic() {
		return new HammerWeaponMechanic();
	}
}
