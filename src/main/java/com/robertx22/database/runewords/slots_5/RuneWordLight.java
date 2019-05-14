package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.spell_buffs.LightFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.runes.AnoItem;
import com.robertx22.items.runes.DosItem;
import com.robertx22.items.runes.GohItem;
import com.robertx22.items.runes.ItaItem;
import com.robertx22.items.runes.XahItem;
import com.robertx22.items.runes.base.BaseRuneItem;

public class RuneWordLight extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new LightFlat(), new HealthRegenFlat(), new HealthFlat(), new FireResistFlat());
    }

    @Override
    public String GUID() {
	return "Light";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new ItaItem(0), new AnoItem(0), new DosItem(0), new XahItem(0), new GohItem(0));
    }

    @Override
    public String unlocName() {
	return "light";
    }

}
