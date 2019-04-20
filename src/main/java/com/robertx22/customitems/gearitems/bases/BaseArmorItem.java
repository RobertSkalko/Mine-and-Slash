package com.robertx22.customitems.gearitems.bases;

import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.uncommon.utilityclasses.Utils;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseArmorItem extends ItemArmor implements IGearItem {

    public static int MAX_GEAR_DURABILITY = 1000;

    static int Enchantability = 10;

    static ItemArmor.ArmorMaterial COMMON_MAT = EnumHelper.addArmorMaterial("common", Utils.setLocation("common"), 20,
	    new int[] { 1, 2, 3, 1 }, Enchantability, null, 0);

    static ItemArmor.ArmorMaterial UNCOMMON_MAT = EnumHelper.addArmorMaterial("uncommon", Utils.setLocation("uncommon"),
	    20, new int[] { 1, 4, 5, 2 }, Enchantability, null, 0);

    static ItemArmor.ArmorMaterial RARE_MAT = EnumHelper.addArmorMaterial("rare", Utils.setLocation("rare"), 20,
	    new int[] { 1, 4, 5, 2 }, Enchantability, null, 0);

    static ItemArmor.ArmorMaterial EPIC_MAT = EnumHelper.addArmorMaterial("epic", Utils.setLocation("epic"), 20,
	    new int[] { 2, 5, 6, 2 }, Enchantability, null, 0);

    static ItemArmor.ArmorMaterial LEGENDARY_MAT = EnumHelper.addArmorMaterial("legendary",
	    Utils.setLocation("legendary"), 20, new int[] { 2, 5, 6, 2 }, Enchantability, null, 0);

    static ItemArmor.ArmorMaterial MYTHICAL_MAT = EnumHelper.addArmorMaterial("mythical", Utils.setLocation("mythical"),
	    30, new int[] { 3, 6, 8, 3 }, Enchantability, null, 0);

    static ItemArmor.ArmorMaterial UNIQUE_MAT = EnumHelper.addArmorMaterial("unique", Utils.setLocation("unique"), 30,
	    new int[] { 3, 6, 8, 3 }, Enchantability, null, 0);

    public abstract String Name();

    public BaseArmorItem(int rarity, EntityEquipmentSlot slot) {
	super(GetMat(rarity), 0, slot);
	this.setMaxStackSize(1);
	this.setMaxDamage(MAX_GEAR_DURABILITY);

    }

    private static ItemArmor.ArmorMaterial GetMat(int rarity) {

	if (rarity == 0) {
	    return COMMON_MAT;
	} else if (rarity == 1) {
	    return UNCOMMON_MAT;
	} else if (rarity == 2) {
	    return RARE_MAT;
	} else if (rarity == 3) {
	    return EPIC_MAT;
	} else if (rarity == 4) {
	    return LEGENDARY_MAT;
	} else if (rarity == 5) {
	    return MYTHICAL_MAT;
	} else if (rarity == new UniqueItem().Rank()) {
	    return UNIQUE_MAT;
	}

	return COMMON_MAT;

    }

}