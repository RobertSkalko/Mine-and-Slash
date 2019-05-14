package com.robertx22.database.runewords.slots_2;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.ArmorFlat;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stats.StatMod;
import com.robertx22.items.runes.DosItem;
import com.robertx22.items.runes.VohItem;
import com.robertx22.items.runes.base.BaseRuneItem;

public class RuneWordMagma extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new FireResistFlat(), new ArmorFlat());
    }

    @Override
    public String GUID() {
	return "Magma";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new DosItem(0), new VohItem(0));
    }

    @Override
    public String unlocName() {
	return "magma";
    }

}
