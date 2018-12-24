package com.robertx22.customitems.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.MajorArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.WaterResistFlat;
import com.robertx22.stats.StatMod;

public class DefenseInfusion extends BaseInfusionItem {

    public DefenseInfusion() {
	super("defense_infusion");

    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new DodgeFlat(), new MajorArmorFlat());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new ArmorFlat(), new FireResistFlat(), new WaterResistFlat(), new ThunderResistFlat(),
		new NatureResistFlat(), new DodgeFlat());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return Arrays.asList(new DodgeFlat(), new ArmorFlat());
    }

}
