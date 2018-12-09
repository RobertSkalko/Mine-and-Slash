package com.robertx22.unique_items.bracelets;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.elemental.conversions.ThunderToNatureConvFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.database.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.stats.StatMod;
import com.robertx22.unique_items.bases.BaseUniqueBracelet;

public class BraceletThunderNature extends BaseUniqueBracelet {

    public BraceletThunderNature() {

    }

    @Override
    public int Tier() {
	return 16;
    }

    @Override
    public String GUID() {
	return "braceletthundernature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
	return Arrays.asList(new ThunderToNatureConvFlat(), new NatureResistFlat(), new ThunderResistFlat(),
		new ManaFlat(), new LessCriticalHitPercent(), new LessDodgePercent());
    }

}