package com.robertx22.database.stat_types.traits.low_dodge;

import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.database.stat_types.traits.bases.BaseTraitLowDodge;
import com.robertx22.saveclasses.Unit;

public class LowDodgeAddArmor extends BaseTraitLowDodge {

    @Override
    public void affectStats(Unit unit) {
	unit.MyStats.get(Armor.GUID).Multi += 15;

    }

    @Override
    public String descSuffix() {
	return " Armor +15% Multi";
    }

    @Override
    public String Guid() {
	return "LowDodgeAddArmor";
    }

    @Override
    public String Name() {
	return "Armor On Low Dodge";
    }

}
