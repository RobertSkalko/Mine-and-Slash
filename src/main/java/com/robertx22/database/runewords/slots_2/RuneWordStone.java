package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.AnoItem;
import com.robertx22.customitems.runes.ItaItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.percent.ArmorPercent;
import com.robertx22.stats.StatMod;

public class RuneWordStone extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new ArmorPercent());
    }

    @Override
    public String GUID() {
	return "Stone";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new ItaItem(0), new AnoItem(0));
    }

    @Override
    public String unlocName() {
	return "stone";
    }

}
