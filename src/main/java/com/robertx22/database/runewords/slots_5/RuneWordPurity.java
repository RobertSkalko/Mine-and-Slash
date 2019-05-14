package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.spell_buffs.PurityFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.runes.AnoItem;
import com.robertx22.items.runes.CenItem;
import com.robertx22.items.runes.DosItem;
import com.robertx22.items.runes.GohItem;
import com.robertx22.items.runes.MosItem;
import com.robertx22.items.runes.base.BaseRuneItem;

public class RuneWordPurity extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new PurityFlat(), new HealthRegenFlat(), new HealthFlat(), new ManaRegenFlat());
    }

    @Override
    public String GUID() {
	return "Purity";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new GohItem(0), new AnoItem(0), new DosItem(0), new CenItem(0), new MosItem(0));
    }

    @Override
    public String unlocName() {
	return "purity";
    }

}
