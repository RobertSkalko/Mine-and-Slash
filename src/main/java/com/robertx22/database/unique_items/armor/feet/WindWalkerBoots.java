package com.robertx22.database.unique_items.armor.feet;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.multi.MajorDodgeMulti;
import com.robertx22.database.stat_mods.multi.ele_minus.MajorMinusFireResistMulti;
import com.robertx22.database.stat_mods.multi.ele_minus.MajorMinusWaterResistMulti;
import com.robertx22.database.unique_items.BaseUniqueItem;
import com.robertx22.stats.StatMod;

import net.minecraft.item.Item;

public class WindWalkerBoots extends BaseUniqueItem {

	@Override
	public String GUID() {
		return "WindWalkerBoots";
	}

	@Override
	public String Name() {
		return "Windwalker's Boots";
	}

	@Override
	public String Desc() {
		return "Flee like the wind, but beware of fire and ice..";
	}

	@Override
	public List<StatMod> UniqueStats() {
		return Arrays.asList(new HealthFlat(), new DodgeFlat(), new MajorDodgeMulti(), new MajorMinusFireResistMulti(),
				new MajorMinusWaterResistMulti());
	}

	@Override
	public Item ItemStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int Tier() {
		return 1;
	}

}
