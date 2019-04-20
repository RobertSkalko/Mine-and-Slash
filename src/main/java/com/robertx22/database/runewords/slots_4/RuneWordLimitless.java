package com.robertx22.database.runewords.slots_4;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.ItaItem;
import com.robertx22.customitems.runes.VohItem;
import com.robertx22.customitems.runes.XahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.percent.DodgePercent;
import com.robertx22.database.stat_mods.spell_buffs.EnergyRegenBuffFlat;
import com.robertx22.stats.StatMod;

public class RuneWordLimitless extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new EnergyRegenBuffFlat(), new EnergyRegenFlat(), new DodgePercent());
    }

    @Override
    public String GUID() {
	return "Limitless";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new VohItem(0), new ItaItem(0), new BerItem(0), new XahItem(0));
    }

    @Override
    public String unlocName() {
	return "limitless";
    }

}
