package com.robertx22.database.stat_types.traits.low_crit_hit;

import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.database.stat_types.traits.bases.BaseTraitLowCritHit;
import com.robertx22.saveclasses.Unit;

public class LowCritHitAddDodge extends BaseTraitLowCritHit {

    @Override
    public void affectStats(Unit unit) {
	unit.MyStats.get(Dodge.GUID).BaseFlat += 15;

    }

    @Override
    public String descSuffix() {
	return " Dodge +15%";
    }

    @Override
    public String Guid() {
	return "LowCritHitAddDodgeFlat";
    }

    @Override
    public String Name() {
	return "Dodge On Low Crit";
    }

}
