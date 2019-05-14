package com.robertx22.database.runewords.slots_4;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.database.stat_mods.spell_buffs.ZephyrFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.runes.BerItem;
import com.robertx22.items.runes.CenItem;
import com.robertx22.items.runes.MosItem;
import com.robertx22.items.runes.XahItem;
import com.robertx22.items.runes.base.BaseRuneItem;

public class RuneWordZephyr extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new ZephyrFlat(), new EnergyRegenFlat(), new ThunderResistFlat(), new ArmorFlat());
    }

    @Override
    public String GUID() {
	return "Zephyr";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new MosItem(0), new XahItem(0), new CenItem(0), new BerItem(0));
    }

    @Override
    public String unlocName() {
	return "zephyr";
    }

}
