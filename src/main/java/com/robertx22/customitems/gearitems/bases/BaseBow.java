package com.robertx22.customitems.gearitems.bases;

public abstract class BaseBow extends net.minecraft.item.ItemBow implements IGearItem {

	public abstract String Name();

	public BaseBow() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(BaseArmorItem.MAX_GEAR_DURABILITY);

	}
}