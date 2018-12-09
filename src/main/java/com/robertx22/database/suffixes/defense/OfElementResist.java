package com.robertx22.database.suffixes.defense;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.saveclasses.gearitem.gear_bases.Suffix;
import com.robertx22.stats.StatMod;

public class OfElementResist extends Suffix {

    public OfElementResist() {
    }

    @Override
    public String GUID() {
	return "Of Element Resist";
    }

    @Override
    public List<StatMod> StatMods() {

	return Arrays.asList(new FireResistFlat(), new WaterResistFlat(), new NatureResistFlat(),
		new ThunderResistFlat());

    }

    @Override
    public int Weight() {
	return this.EpicWeight;
    }

}
