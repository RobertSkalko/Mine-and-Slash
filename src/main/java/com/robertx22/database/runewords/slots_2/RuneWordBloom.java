package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.AnoItem;
import com.robertx22.customitems.runes.GohItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.stats.StatMod;

public class RuneWordBloom extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new HealthPercent());
    }

    @Override
    public String GUID() {
	return "Bloom";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new AnoItem(0), new GohItem(0));
    }

    @Override
    public String unlocName() {
	return "bloom";
    }

}
