package com.robertx22.uncommon.capability.bases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.db_lists.Sets;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.gearitem.gear_bases.Set;
import com.robertx22.stats.IStatConversion;
import com.robertx22.stats.IStatTransfer;
import com.robertx22.stats.Stat;
import com.robertx22.stats.StatMod;
import com.robertx22.stats.Trait;
import com.robertx22.uncommon.SLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Gear;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerStatUtils {

    public static void AddPlayerBaseStats(UnitData data, Unit unit) {

	unit.MyStats.get("Thunder Resist").Flat += (int) (ModConfig.BasePlayerStats.thunder_resist
		+ data.getLevel() * ModConfig.BasePlayerStats.thunder_resist_per_level);

	unit.MyStats.get("Water Penetration").Flat += (int) (ModConfig.BasePlayerStats.water_penetration
		+ data.getLevel() * ModConfig.BasePlayerStats.water_penetration_per_level);

	unit.MyStats.get("Physical Damage").Flat += (int) (ModConfig.BasePlayerStats.physical_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.physical_damage_per_level);

	unit.MyStats.get("Mana").Flat += (int) (ModConfig.BasePlayerStats.mana
		+ data.getLevel() * ModConfig.BasePlayerStats.mana_per_level);

	unit.MyStats.get("Dodge").Flat += (int) (ModConfig.BasePlayerStats.dodge
		+ data.getLevel() * ModConfig.BasePlayerStats.dodge_per_level);

	unit.MyStats.get("Energy").Flat += (int) (ModConfig.BasePlayerStats.energy
		+ data.getLevel() * ModConfig.BasePlayerStats.energy_per_level);

	unit.MyStats.get("Spell Fire Damage").Flat += (int) (ModConfig.BasePlayerStats.spell_fire_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.spell_fire_damage_per_level);

	unit.MyStats.get("Water Resist").Flat += (int) (ModConfig.BasePlayerStats.water_resist
		+ data.getLevel() * ModConfig.BasePlayerStats.water_resist_per_level);

	unit.MyStats.get("Hammer Damage").Flat += (int) (ModConfig.BasePlayerStats.hammer_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.hammer_damage_per_level);

	unit.MyStats.get("Fire Resist").Flat += (int) (ModConfig.BasePlayerStats.fire_resist
		+ data.getLevel() * ModConfig.BasePlayerStats.fire_resist_per_level);

	unit.MyStats.get("Bow Damage").Flat += (int) (ModConfig.BasePlayerStats.bow_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.bow_damage_per_level);

	unit.MyStats.get("Spell Water Damage").Flat += (int) (ModConfig.BasePlayerStats.spell_water_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.spell_water_damage_per_level);

	unit.MyStats.get("Spell Dodge").Flat += (int) (ModConfig.BasePlayerStats.spell_dodge
		+ data.getLevel() * ModConfig.BasePlayerStats.spell_dodge_per_level);

	unit.MyStats.get("All Water Damage").Flat += (int) (ModConfig.BasePlayerStats.all_water_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.all_water_damage_per_level);

	unit.MyStats.get("All Fire Damage").Flat += (int) (ModConfig.BasePlayerStats.All_Fire_Damage
		+ data.getLevel() * ModConfig.BasePlayerStats.All_Fire_Damage_per_level);

	unit.MyStats.get("Mana Regen").Flat += (int) (ModConfig.BasePlayerStats.mana_regen
		+ data.getLevel() * ModConfig.BasePlayerStats.mana_regen_per_level);

	unit.MyStats.get("Spell Thunder Damage").Flat += (int) (ModConfig.BasePlayerStats.spell_thunder_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.spell_thunder_damage_per_level);

	unit.MyStats.get("Life On Hit").Flat += (int) (ModConfig.BasePlayerStats.life_on_hit
		+ data.getLevel() * ModConfig.BasePlayerStats.life_on_hit_per_level);

	unit.MyStats.get("Staff Damage").Flat += (int) (ModConfig.BasePlayerStats.staff_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.staff_damage_per_level);

	unit.MyStats.get("Thunder Penetration").Flat += (int) (ModConfig.BasePlayerStats.thunder_penetration
		+ data.getLevel() * ModConfig.BasePlayerStats.thunder_penetration_per_level);

	unit.MyStats.get("All Thunder Damage").Flat += (int) (ModConfig.BasePlayerStats.all_thunder_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.all_thunder_damage_per_level);

	unit.MyStats.get("Mana On Hit").Flat += (int) (ModConfig.BasePlayerStats.mana_on_hit
		+ data.getLevel() * ModConfig.BasePlayerStats.mana_on_hit_per_level);

	unit.MyStats.get("Attack Thunder Damage").Flat += (int) (ModConfig.BasePlayerStats.attack_thunder_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.attack_thunder_damage_per_level);

	unit.MyStats.get("Nature Penetration").Flat += (int) (ModConfig.BasePlayerStats.nature_penetration
		+ data.getLevel() * ModConfig.BasePlayerStats.nature_penetration_per_level);

	unit.MyStats.get("All Nature Damage").Flat += (int) (ModConfig.BasePlayerStats.all_nature_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.all_nature_damage_per_level);

	unit.MyStats.get("Attack Fire Damage").Flat += (int) (ModConfig.BasePlayerStats.attack_fire_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.attack_fire_damage_per_level);

	unit.MyStats.get("Nature Resist").Flat += (int) (ModConfig.BasePlayerStats.nature_resist
		+ data.getLevel() * ModConfig.BasePlayerStats.nature_resist_per_level);

	unit.MyStats.get("Sword Damage").Flat += (int) (ModConfig.BasePlayerStats.sword_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.sword_damage_per_level);

	unit.MyStats.get("Health Regen").Flat += (int) (ModConfig.BasePlayerStats.health_regen
		+ data.getLevel() * ModConfig.BasePlayerStats.health_regen_per_level);

	unit.MyStats.get("Fire Penetration").Flat += (int) (ModConfig.BasePlayerStats.fire_penetration
		+ data.getLevel() * ModConfig.BasePlayerStats.fire_penetration_per_level);

	unit.MyStats.get("Armor").Flat += (int) (ModConfig.BasePlayerStats.armor
		+ data.getLevel() * ModConfig.BasePlayerStats.armor_per_level);

	unit.MyStats.get("Attack Nature Damage").Flat += (int) (ModConfig.BasePlayerStats.attack_nature_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.attack_nature_damage_per_level);

	unit.MyStats.get("Health").Flat += (int) (ModConfig.BasePlayerStats.health
		+ data.getLevel() * ModConfig.BasePlayerStats.health_per_level);

	unit.MyStats.get("Attack Water Damage").Flat += (int) (ModConfig.BasePlayerStats.attack_water_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.attack_water_damage_per_level);

	unit.MyStats.get("Axe Damage").Flat += (int) (ModConfig.BasePlayerStats.axe_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.axe_damage_per_level);

	unit.MyStats.get("Critical Hit").Flat += (int) (ModConfig.BasePlayerStats.critical_hit
		+ data.getLevel() * ModConfig.BasePlayerStats.critical_hit_per_level);

	unit.MyStats.get("Spell Nature Damage").Flat += (int) (ModConfig.BasePlayerStats.spell_nature_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.spell_nature_damage_per_level);

	unit.MyStats.get("Energy Regen").Flat += (int) (ModConfig.BasePlayerStats.energy_regen
		+ data.getLevel() * ModConfig.BasePlayerStats.energy_regen_per_level);

	unit.MyStats.get("Armor Penetration").Flat += (int) (ModConfig.BasePlayerStats.armor_penetration
		+ data.getLevel() * ModConfig.BasePlayerStats.armor_penetration_per_level);

	unit.MyStats.get("Critical Damage").Flat += (int) (ModConfig.BasePlayerStats.critical_damage
		+ data.getLevel() * ModConfig.BasePlayerStats.critical_damage_per_level);

	unit.MyStats.get("Lifesteal").Flat += (int) (ModConfig.BasePlayerStats.lifesteal
		+ data.getLevel() * ModConfig.BasePlayerStats.lifesteal_per_level);

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

    public static List<GearItemData> getEquipsExcludingWeapon(EntityLivingBase entity) {

	List<ItemStack> list = new ArrayList<ItemStack>();

	for (ItemStack stack : entity.getArmorInventoryList()) {
	    if (stack != null) {
		list.add(stack);
	    }
	}

	if (entity instanceof EntityPlayer) {
	    IBaublesItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayer) entity);

	    for (int i = 0; i < baubles.getSlots(); i++) {
		ItemStack stack = baubles.getStackInSlot(i);
		if (stack != null) {
		    list.add(stack);
		}

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

    public static void CountWornSets(Entity entity, List<GearItemData> gears, Unit unit) {

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

    public static void AddAllSetStats(Entity entity, Unit unit, int level) {

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

    public static void AddAllGearStats(Entity entity, List<GearItemData> gears, Unit unit, int level) {

	for (GearItemData gear : gears) {
	    if (gear.level > level) {
		if (entity instanceof EntityPlayer) {
		    entity.sendMessage(SLOC.chat("too_high_level"));
		}
	    } else {

		List<LevelAndStats> levelstats = gear.GetAllStats(gear.level);
		for (LevelAndStats datas : levelstats) {
		    for (StatModData data : datas.mods) {
			StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Guid());
			if (stat == null) {
			    System.out.println(
				    "Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Guid());
			} else {
			    stat.Add(data, datas.level);

			}
		    }
		}
	    }
	}
    }

}
