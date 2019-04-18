package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.AnoItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.DosItem;
import com.robertx22.customitems.runes.GohItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.spell_buffs.PurityFlat;
import com.robertx22.stats.StatMod;

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
