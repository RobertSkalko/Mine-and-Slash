package com.robertx22.database.runewords.slots_3;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.GohItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.RahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.stats.StatMod;

public class RuneWordThief extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new DodgeFlat(), new CriticalDamageFlat());
    }

    @Override
    public String GUID() {
	return "Thief";
    }

    @Override
    public List<BaseRuneItem> runes() {

	return Arrays.asList(new RahItem(0), new MosItem(0), new GohItem(0));

    }

    @Override
    public String unlocName() {
	return "thief";
    }

}
