package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.VohItem;
import com.robertx22.customitems.runes.XahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.DodgeFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.NatureResistFlat;
import com.robertx22.database.stat_mods.percent.DodgePercent;
import com.robertx22.database.stat_mods.spell_buffs.GhostProjectileFlat;
import com.robertx22.stats.StatMod;

public class RuneWordGhost extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new GhostProjectileFlat(), new DodgeFlat(), new DodgePercent(), new NatureResistFlat());
    }

    @Override
    public String GUID() {
	return "Ghost";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new VohItem(0), new XahItem(0), new BerItem(0), new XahItem(0), new CenItem(0));
    }

    @Override
    public String unlocName() {
	return "ghost";
    }

}
