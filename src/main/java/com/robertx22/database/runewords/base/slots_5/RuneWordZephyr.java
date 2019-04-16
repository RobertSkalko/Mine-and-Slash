package com.robertx22.database.runewords.base.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.MosItem;
import com.robertx22.customitems.runes.XahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.base.RuneWord;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.stats.StatMod;

public class RuneWordZephyr extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new EnergyRegenFlat(), new ThunderResistFlat());
    }

    @Override
    public String name() {
	return "Zephyr";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new MosItem(0), new XahItem(0), new CenItem(0), new BerItem(0), new XahItem(0));
    }

}
