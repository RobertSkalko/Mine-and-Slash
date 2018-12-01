package com.robertx22.database.stat_types.traits.low_dodge;

import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.database.stat_types.traits.bases.BaseTraitLowDodge;
import com.robertx22.saveclasses.Unit;

public class LowDodgeAddCritHit extends BaseTraitLowDodge {

    @Override
    public void affectStats(Unit unit) {
	unit.MyStats.get(CriticalHit.GUID).Flat += 10;

    }

    @Override
    public String descSuffix() {
	return " Critical Hit +10%";
    }

    @Override
    public String Guid() {
	return "LowDodgeAddCritHit";
    }

    @Override
    public String Name() {
	return "Crit On Low Dodge";
    }

}
