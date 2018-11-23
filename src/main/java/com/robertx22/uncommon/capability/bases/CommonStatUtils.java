package com.robertx22.uncommon.capability.bases;

import java.util.List;

import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saveclasses.effects.StatusEffectData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.mapitem.MapAffixData;

public class CommonStatUtils {

	public static void AddStatusEffectStats(Unit unit, int level) {

		for (StatusEffectData status : unit.statusEffects.values()) {
			List<StatModData> datas = status.GetAllStats(level);
			for (StatModData data : datas) {
				StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Name());
				if (stat == null) {
					System.out.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Name());
				} else {
					stat.Add(data, level);

				}
			}
		}

	}

	public static void AddMapAffixStats(Unit unit, int level) {

		for (MapAffixData status : unit.mapAffixes.values()) {
			List<StatModData> datas = status.GetAllStats();
			for (StatModData data : datas) {
				StatData stat = unit.MyStats.get(data.GetBaseMod().GetBaseStat().Name());
				if (stat == null) {
					System.out.println("Error! can't load a stat called: " + data.GetBaseMod().GetBaseStat().Name());
				} else {
					stat.Add(data, level);

				}
			}
		}

	}

}
