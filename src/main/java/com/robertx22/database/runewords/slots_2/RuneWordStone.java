package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.ZohItem;
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
    public String name() {
	return "Stone";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new ZohItem(0), new CenItem(0));
    }

}
