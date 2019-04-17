package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.ZohItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.elemental.resist.FireResistFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.database.stat_mods.spell_buffs.LightFlat;
import com.robertx22.stats.StatMod;

public class RuneWordLight extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new LightFlat(), new HealthRegenFlat(), new HealthFlat(), new FireResistFlat());
    }

    @Override
    public String name() {
	return "Light";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new ZohItem(0));
    }

}
