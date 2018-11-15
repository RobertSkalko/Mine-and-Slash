package com.robertx22.customitems.gearitems.bases;

import java.util.HashMap;

import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.Utils;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseArmorItem extends ItemArmor {

	public static int MAX_GEAR_DURABILITY = 750;

	static int[] nums = new int[] { 3, 3, 3, 3 };

	static int Enchantability = 10;

	static ItemArmor.ArmorMaterial COMMON_MAT = EnumHelper.addArmorMaterial("common", Utils.setLocation("common"), 20,
			nums, Enchantability, null, 0);

	static ItemArmor.ArmorMaterial UNCOMMON_MAT = EnumHelper.addArmorMaterial("uncommon", Utils.setLocation("uncommon"),
			20, nums, Enchantability, null, 0);

	static ItemArmor.ArmorMaterial RARE_MAT = EnumHelper.addArmorMaterial("rare", Utils.setLocation("rare"), 20, nums,
			Enchantability, null, 0);

	static ItemArmor.ArmorMaterial EPIC_MAT = EnumHelper.addArmorMaterial("epic", Utils.setLocation("epic"), 20, nums,
			Enchantability, null, 0);

	static ItemArmor.ArmorMaterial LEGENDARY_MAT = EnumHelper.addArmorMaterial("legendary",
			Utils.setLocation("legendary"), 20, nums, Enchantability, null, 0);

	static ItemArmor.ArmorMaterial MYTHICAL_MAT = EnumHelper.addArmorMaterial("mythical", Utils.setLocation("mythical"),
			20, nums, Enchantability, null, 0);

	public abstract String Name();

	public BaseArmorItem(int rarity, HashMap<Integer, Item> map, EntityEquipmentSlot slot) {
		super(GetMat(rarity), 0, slot);
		this.setMaxStackSize(1);
		this.setMaxDamage(MAX_GEAR_DURABILITY);
		RegisterItemUtils.RegisterItemName(this, Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

	private static ItemArmor.ArmorMaterial GetMat(int rarity) {

		if (rarity == 0) {
			return COMMON_MAT;
		}
		if (rarity == 1) {
			return UNCOMMON_MAT;
		}
		if (rarity == 2) {
			return RARE_MAT;
		}
		if (rarity == 3) {
			return EPIC_MAT;
		}
		if (rarity == 4) {
			return LEGENDARY_MAT;
		}
		if (rarity == 5) {
			return MYTHICAL_MAT;
		}

		return COMMON_MAT;

	}

}