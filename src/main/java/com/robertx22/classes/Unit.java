package com.robertx22.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.stats.types.Health;
import com.robertx22.gearitem.GearItem;
import com.robertx22.saving.Saving;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatModData;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public class Unit {

	public EntityLiving Entityliving;

	public Unit(EntityLiving entity) {
		this.Entityliving = entity;
	}

	public HashMap<Class, Stat> Stats = new HashMap<Class, Stat>() {
		{
			put(Health.class, new Health());

		}
	};

	public boolean StatsDirty = true;

	public List<GearItem> GetEquips() {

		List<ItemStack> list = new ArrayList<ItemStack>();

		list.addAll((Collection<? extends ItemStack>) Entityliving.getArmorInventoryList());
		list.add(Entityliving.getHeldItemMainhand());

		List<GearItem> gearitems = new ArrayList<GearItem>();

		for (ItemStack stack : list) {

			GearItem gear = Saving.Load(stack.getTagCompound(), GearItem.class);

			if (gear != null) {
				gearitems.add(gear);
			}

		}

		return gearitems;

	}

	private void ClearStats() {
		for (Stat stat : Stats.values()) {
			stat.Clear();
		}
	}

	public void RecalculateStats() {

		ClearStats();

		List<GearItem> gears = GetEquips();

		for (GearItem gear : gears) {

			List<StatModData> datas = gear.GetAllStats();

			for (StatModData data : datas) {

				int val = data.GetActualVal(gear.level);

				Stats.get(data.GetBaseStat().getClass()).Add(val, data.type);

			}
		}

	}

}
