package com.robertx22.database.runewords.slots_4;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.AnoItem;
import com.robertx22.customitems.runes.CenItem;
import com.robertx22.customitems.runes.DosItem;
import com.robertx22.customitems.runes.RahItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.database.stat_mods.spell_buffs.ManaRegenBuffFlat;
import com.robertx22.stats.StatMod;

public class RuneWordProfoundSea extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new ManaRegenBuffFlat(), new ManaRegenFlat(), new ManaFlat());
    }

    @Override
    public String GUID() {
	return "Profound Sea";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new DosItem(0), new AnoItem(0), new RahItem(0), new CenItem(0));

    }

    @Override
    public String unlocName() {
	return "profound_sea";
    }

}
