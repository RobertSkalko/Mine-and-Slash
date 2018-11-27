package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseWeaponItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.SwordWeaponMechanic;

import net.minecraft.item.Item;

public class ItemSword extends BaseWeaponItem implements IWeapon {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemSword() {

	}

	@Override
	public String Name() {
		return "Sword";
	}

	@Override
	public WeaponMechanic mechanic() {
		return new SwordWeaponMechanic();
	}
}
