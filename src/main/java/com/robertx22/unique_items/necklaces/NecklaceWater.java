package com.robertx22.unique_items.necklaces;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.spell_dmg.SpellWaterDamageFlat;
import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.database.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueNecklace;

public class NecklaceWater extends BaseUniqueNecklace {

    public NecklaceWater() {

    }

    @Override
    public int Tier() {
	return 7;
    }

    @Override
    public String name() {
	return "Amulet of the Glacier";
    }

    @Override
    public String GUID() {
	return "necklacewater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new SpellWaterDamageFlat(), new ArmorFlat(), new ArmorPercent(), new WaterResistFlat(),
		new SpellWaterDamagePercent(), new LessDodgePercent());
    }

    @Override
    public String description() {
	return "Crystal clear and yet so incredibly tough.";
    }

}
