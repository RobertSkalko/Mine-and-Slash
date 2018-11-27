package com.robertx22.customitems.gearitems.weapons;

import java.util.HashMap;

import com.robertx22.customitems.gearitems.bases.BaseBow;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.gearitems.bases.WeaponMechanic;
import com.robertx22.customitems.gearitems.weapon_mechanics.BowWeaponMechanic;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ItemBow extends BaseBow implements IWeapon {
	public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

	public ItemBow() {

	}

	@Override
	public String Name() {
		return "Bow";
	}

	@Override
	public WeaponMechanic mechanic() {
		return new BowWeaponMechanic();
	}
}
