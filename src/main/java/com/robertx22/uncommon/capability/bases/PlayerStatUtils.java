package com.robertx22.uncommon.capability.bases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stat_types.resources.Health;
import com.robertx22.db_lists.Sets;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.IStatConversion;
import com.robertx22.stats.IStatTransfer;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.capability.EntityData.UnitData;
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

    public static void CalcTraits(UnitData unit) {
	for (StatData statdata : unit.getUnit().MyStats.values()) {
	    Stat stat = statdata.GetStat();
	    if (statdata.Value > 0) {
		if (stat instanceof Trait) {
		    Trait affects = (Trait) stat;
		    affects.TryAffectOtherStats(unit);

		}
	    }
	}

    }

    /**
     * A unit copy is needed so there's no randomness to stat transfers and
     * conversions. All changes are based on old copy but applied to the unit that's
     * used
     */
    public static void CalcStatConversionsAndTransfers(Unit copy, Unit unit) {

	for (StatData statdata : copy.MyStats.values()) {

	    Stat stat = statdata.GetStat();
	    if (statdata.Value > 0) {
		if (stat instanceof IStatConversion) {
		    IStatConversion affects = (IStatConversion) stat;
		    affects.convertStats(copy, unit, statdata);
		}
		if (stat instanceof IStatTransfer) {
		    IStatTransfer affects = (IStatTransfer) stat;
		    affects.transferStats(copy, unit, statdata);
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

    public static void CountWornSets(EntityLivingBase entity, List<GearItemData> gears, Unit unit) {

	unit.WornSets = new HashMap<String, Integer>();

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

		String name = mod.GetBaseStat().Guid();
		if (unit.MyStats.containsKey(name)) {
		    unit.MyStats.get(name).Add(data, level);
		}
	    }

	}

    }

    public static void AddAllGearStats(EntityLivingBase entity, List<GearItemData> gears, Unit unit, int level) {

	for (GearItemData gear : gears) {
	    if (gear.level > level) {
		entity.sendMessage(
			new TextComponentString(gear.GetDisplayName() + " is too high level for you, no stats added!"));
	    } else {

		List<StatModData> datas = gear.GetAllStats(gear.level);
		for (StatModData data : datas) {
		    StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Guid());
		    if (stat == null) {
			System.out
				.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Guid());
		    } else {
			stat.Add(data, gear.level);

		    }
		}
	    }
	}
    }

}
