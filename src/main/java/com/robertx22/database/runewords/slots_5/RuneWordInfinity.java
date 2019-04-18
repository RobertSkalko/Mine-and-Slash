package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.AnoItem;
import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.DosItem;
import com.robertx22.customitems.runes.XahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.resources.EnergyFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.stats.StatMod;

public class RuneWordInfinity extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new EnergyFlat(), new EnergyRegenFlat(), new EnergyRegenPercent());
    }

    @Override
    public String GUID() {
	return "Infinity";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new CenItem(0), new DosItem(0), new AnoItem(0), new BerItem(0), new XahItem(0));
    }

    @Override
    public String unlocName() {
	return "infinity";
    }

}
