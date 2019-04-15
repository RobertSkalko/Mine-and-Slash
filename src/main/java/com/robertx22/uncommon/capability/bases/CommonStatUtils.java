package com.robertx22.uncommon.capability.bases;

import java.util.HashMap;
import java.util.List;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class CommonStatUtils {

    public static void AddStatusEffectStats(Unit unit, int level) {

	for (StatusEffectData status : unit.statusEffects.values()) {
	    List<LevelAndStats> levelsandstats = status.GetAllStats(level);
	    for (LevelAndStats levelstat : levelsandstats) {
		for (StatModData data : levelstat.mods) {

		    StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Guid());
		    if (stat == null) {
			System.out
				.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Guid());
		    } else {
			stat.Add(data, level);
		    }
		}
	    }
	}

    }

    public static Unit addMapAffixes(IWorldData worlddata, EntityLivingBase entity, Unit unit, UnitData endata) {
	unit.mapAffixes = new HashMap<String, MapAffixData>();

	if (worlddata.isMapWorld()) {

	    AffectedEntities affected = null;

	    if (entity instanceof EntityPlayer) {
		affected = AffectedEntities.Players;
	    } else {
		affected = AffectedEntities.Mobs;
	    }

	    for (MapAffixData affix : worlddata.getMap().getAllAffixesThatAffect(affected)) {
		unit.mapAffixes.put(affix.GUID, affix);
	    }

	    for (MapAffixData affix : worlddata.getMap().getAllAffixesThatAffect(AffectedEntities.All)) {
		unit.mapAffixes.put(affix.GUID, affix);
	    }

	}

	return unit;
    }

    public static void AddMapAffixStats(Unit unit, int level) {

	for (MapAffixData status : unit.mapAffixes.values()) {
	    List<StatModData> datas = status.GetAllStats();
	    for (StatModData data : datas) {
		StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Guid());
		if (stat == null) {
		    System.out.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Guid());
		} else {
		    stat.Add(data, level);

		}
	    }
	}

    }

}
