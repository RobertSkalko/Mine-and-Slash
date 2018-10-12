package com.robertx22.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.robertx22.database.stats.types.Armor;
import com.robertx22.database.stats.types.CriticalDamage;
import com.robertx22.database.stats.types.CriticalHit;
import com.robertx22.database.stats.types.Damage;
import com.robertx22.database.stats.types.Health;
import com.robertx22.database.stats.types.MaxDamage;
import com.robertx22.database.stats.types.MinDamage;
import com.robertx22.gearitem.GearItem;
import com.robertx22.saving.Saving;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatModData;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class Unit {

	public EntityLivingBase entity;

	public Unit(EntityLivingBase entity) {
		this.entity = entity;
	}

	public HashMap<Class, Stat> Stats = new HashMap<Class, Stat>() {
		{
			put(Health.class, new Health());
			put(Damage.class, new Damage());
			put(MinDamage.class, new MinDamage());
			put(MaxDamage.class, new MaxDamage());
			put(Armor.class, new Armor());
			put(CriticalHit.class, new CriticalHit());
			put(CriticalDamage.class, new CriticalDamage());

		}
	};

	public boolean StatsDirty = true;

	public List<GearItem> GetEquips() {

		List<ItemStack> list = new ArrayList<ItemStack>();

		list.addAll((Collection<? extends ItemStack>) entity.getEquipmentAndArmor());
		list.add(entity.getHeldItemMainhand());

		List<GearItem> gearitems = new ArrayList<GearItem>();

		for (ItemStack stack : list) {

			GearItem gear = Saving.Load(stack.getTagCompound(), GearItem.class);

			if (gear != null) {
				gearitems.add(gear);

				System.out.println("Gearitemfound" + gear.toString());
			}

		}

		return gearitems;

	}

	private void ClearStats() {
		for (Stat stat : Stats.values()) {
			stat.Clear();
		}
	}

	private void AddAllGearStats() {

		List<GearItem> gears = GetEquips();

		for (GearItem gear : gears) {

			List<StatModData> datas = gear.GetAllStats();

			for (StatModData data : datas) {

				int val = data.GetActualVal(gear.level);

				Stats.get(data.GetBaseStat().getClass()).Add(val, data.type);

			}
		}
	}

	public void RecalculateStats() {

		ClearStats();

		AddAllGearStats();

	}

}
