package com.robertx22.spells.bases;

import com.robertx22.db_lists.Stats;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;

public class EffectCalculation {

	public String Stat;
	public float Multi;

	public EffectCalculation(String stat, float multi) {
		super();
		Stat = stat;
		Multi = multi;
	}

	public Stat GetStat() {
		return Stats.All.get(Stat);
	}

	public int GetValue(Unit unit) {

		return (int) (unit.MyStats.get(Stat).Value * Multi);

	}

}
