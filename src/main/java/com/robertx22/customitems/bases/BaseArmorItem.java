package com.robertx22.customitems.bases;

import java.util.HashMap;

import com.robertx22.customitems.oldreplacesoon.NewBlocks;
import com.robertx22.uncommon.utilityclasses.OnItemCreatedUtils;
import com.robertx22.uncommon.utilityclasses.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseArmorItem extends ItemArmor {

	static int[] nums = new int[] { 3, 3, 3, 3 };

	static ItemArmor.ArmorMaterial COMMON_MAT = EnumHelper.addArmorMaterial("common", Utils.setLocation("common"), 20,
			nums, 0, null, 0);

	static ItemArmor.ArmorMaterial UNCOMMON_MAT = EnumHelper.addArmorMaterial("uncommon", Utils.setLocation("uncommon"),
			20, nums, 0, null, 0);

	static ItemArmor.ArmorMaterial RARE_MAT = EnumHelper.addArmorMaterial("rare", Utils.setLocation("rare"), 20, nums,
			0, null, 0);

	static ItemArmor.ArmorMaterial EPIC_MAT = EnumHelper.addArmorMaterial("epic", Utils.setLocation("epic"), 20, nums,
			0, null, 0);

	static ItemArmor.ArmorMaterial LEGENDARY_MAT = EnumHelper.addArmorMaterial("legendary",
			Utils.setLocation("legendary"), 20, nums, 0, null, 0);

	static ItemArmor.ArmorMaterial MYTHICAL_MAT = EnumHelper.addArmorMaterial("mythical", Utils.setLocation("mythical"),
			20, nums, 0, null, 0);

	public abstract String Name();

	public BaseArmorItem(int rarity, HashMap<Integer, Item> map, EntityEquipmentSlot slot) {
		super(GetMat(rarity), 0, slot);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setCreativeTab(NewBlocks.MyModTab);
		this.setUnlocalizedName(Name().toLowerCase() + rarity);
		this.setRegistryName(Name().toLowerCase() + rarity);

		map.put(rarity, this);
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {

		OnItemCreatedUtils.TryReroll(stack, worldIn);

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