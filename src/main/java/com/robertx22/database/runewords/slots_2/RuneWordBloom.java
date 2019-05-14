package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.percent.HealthPercent;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.runes.AnoItem;
import com.robertx22.items.runes.GohItem;
import com.robertx22.items.runes.base.BaseRuneItem;

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
