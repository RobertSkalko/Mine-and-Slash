package com.robertx22.database.runewords.slots_3;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.ItaItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.RahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.resources.ManaOnHitFlat;
import com.robertx22.database.stat_mods.percent.ManaRegenPercent;
import com.robertx22.stats.StatMod;

public class RuneWordRadiance extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new ManaOnHitFlat(), new ManaRegenPercent());
    }

    @Override
    public String GUID() {
	return "Radiance";
    }

    @Override
    public List<BaseRuneItem> runes() {

	return Arrays.asList(new ItaItem(0), new MosItem(0), new RahItem(0));

    }

    @Override
    public String unlocName() {
	return "radiance";
    }

}