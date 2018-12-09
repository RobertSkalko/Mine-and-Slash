package com.robertx22.unique_items.charms;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.resources.conversions.ManaToEnergyConvFlat;
import com.robertx22.database.stat_mods.percent.much_less.CrippleDodgePercent;
import com.robertx22.database.stat_mods.percent.pene.WaterPenePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueCharm;

public class CharmWater extends BaseUniqueCharm {

    public CharmWater() {

    }

    @Override
    public int Tier() {
	return 18;
    }

    @Override
    public String GUID() {
	return "charmwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new ManaToEnergyConvFlat(), new ArmorFlat(), new WaterPeneFlat(), new WaterPenePercent(),
		new WaterResistFlat(), new CrippleDodgePercent());
    }

}
