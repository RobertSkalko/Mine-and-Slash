package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.stats.StatMod;

public class RuneWordScales extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new NatureResistFlat(), new ArmorFlat());
    }

    @Override
    public String GUID() {
	return "Scales";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new BerItem(0), new CenItem(0));
    }

    @Override
    public String unlocName() {
	return "scales";
    }

}
