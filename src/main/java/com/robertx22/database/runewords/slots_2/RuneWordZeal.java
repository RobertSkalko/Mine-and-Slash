package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.GohItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.percent.EnergyRegenPercent;
import com.robertx22.stats.StatMod;

public class RuneWordZeal extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new EnergyRegenPercent(), new ArmorFlat());
    }

    @Override
    public String GUID() {
	return "Zeal";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new GohItem(0), new MosItem(0));
    }

    @Override
    public String unlocName() {
	return "zeal";
    }

}
