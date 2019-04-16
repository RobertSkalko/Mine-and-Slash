package com.robertx22.database.runewords.base.slots_4;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.DosItem;
import com.robertx22.customitems.runes.RahItem;
import com.robertx22.customitems.runes.XahItem;
import com.robertx22.customitems.runes.YuzItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.base.RuneWord;
import com.robertx22.database.stat_mods.flat.elemental.resist.ThunderResistFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaFlat;
import com.robertx22.database.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.stats.StatMod;

public class RuneWordMagician extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new ManaFlat(), new ManaRegenFlat(), new ThunderResistFlat());
    }

    @Override
    public String name() {
	return "Magician";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new DosItem(0), new YuzItem(0), new XahItem(0), new RahItem(0));

    }

}
