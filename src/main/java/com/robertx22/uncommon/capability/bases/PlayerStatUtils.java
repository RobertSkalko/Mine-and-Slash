package com.robertx22.uncommon.capability.bases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.database.stats.types.resources.Health;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.IAffectsOtherStats;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.datasaving.Gear;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;

public class PlayerStatUtils {

	public static void AddPlayerBaseStats(Unit unit) {

		unit.MyStats.get(Health.GUID).Flat += 10;
		unit.MyStats.get(PhysicalDamage.GUID).Flat += 2;

	}

	public static void CalcTraits(Unit unit) {
		for (StatData stat : unit.MyStats.values()) {
			if (stat.GetStat() instanceof Trait && stat instanceof IAffectsOtherStats) {
				if (stat.Value > 0) {
					IAffectsOtherStats affects = (IAffectsOtherStats) stat;
					affects.TryAffectOtherStats(unit);

				}
			}
		}

	}

	public static List<GearItemData> GetEquips(EntityLivingBase entity) {

		List<ItemStack> list = new ArrayList<ItemStack>();

		for (ItemStack stack : entity.getArmorInventoryList()) {
			if (stack != null) {
				list.add(stack);
			}
		}
		ItemStack weapon = entity.getHeldItemMainhand();
		if (weapon.getItem() instanceof IWeapon) {
			list.add(weapon);
		}

		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) entity);

		for (int i = 0; i < baubles.getSlots(); i++) {
			ItemStack stack = baubles.getStackInSlot(i);
			if (stack != null) {
				list.add(stack);
			}

		}

		List<GearItemData> gearitems = new ArrayList<GearItemData>();

		for (ItemStack stack : list) {

			GearItemData gear = Gear.Load(stack);

			if (gear != null) {
				gearitems.add(gear);

			}

		}

		return gearitems;

	}

	public static void CountWornSets(EntityLivingBase entity, Unit unit) {

		unit.WornSets = new HashMap<String, Integer>();

		List<GearItemData> gears = GetEquips(entity);

		for (GearItemData gear : gears) {
			if (gear.set != null) {
				String set = gear.set.baseSet;

				if (gear.set != null) {
					if (unit.WornSets.containsKey(set)) {
						unit.WornSets.put(set, unit.WornSets.get(set) + 1);
					} else {
						unit.WornSets.put(set, 1);
					}
				}
			}

		}

	}

	public static void AddAllSetStats(EntityLivingBase entity, Unit unit, int level) {

		for (Entry<String, Integer> entry : unit.WornSets.entrySet()) {

			Set set = Sets.All.get(entry.getKey());

			for (StatMod mod : set.GetObtainedMods(unit)) {

				StatModData data = StatModData.Load(mod, set.StatPercent);

				String name = mod.GetBaseStat().Name();
				if (unit.MyStats.containsKey(name)) {
					unit.MyStats.get(name).Add(data, level);
				}
			}

		}

	}

	public static void AddAllGearStats(EntityLivingBase entity, Unit unit, int level) {

		List<GearItemData> gears = GetEquips(entity);

		for (GearItemData gear : gears) {
			if (gear.level > level) {
				entity.sendMessage(
						new TextComponentString(gear.GetDisplayName() + " is too high level for you, no stats added!"));
			} else {

				List<StatModData> datas = gear.GetAllStats(gear.level);
				for (StatModData data : datas) {
					StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Name());
					if (stat == null) {
						System.out
								.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Name());
					} else {
						stat.Add(data, gear.level);

					}
				}
			}
		}
	}

}
