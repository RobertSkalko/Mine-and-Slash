package com.robertx22.database.runewords.slots_5;

import java.util.Arrays;
import java.util.List;

import com.robertx22.customitems.runes.BerItem;
import com.robertx22.customitems.runes.DosItem;
import com.robertx22.customitems.runes.ItaItem;
import com.robertx22.customitems.runes.RahItem;
import com.robertx22.customitems.runes.VohItem;
import com.robertx22.customitems.runes.base.BaseRuneItem;
import com.robertx22.database.runewords.RuneWord;
import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.resources.HealthFlat;
import com.robertx22.database.stat_mods.spell_buffs.HomingFlat;
import com.robertx22.stats.StatMod;

public class RuneWordHoming extends RuneWord {

    @Override
    public List<StatMod> mods() {
	return Arrays.asList(new HomingFlat(), new CriticalHitFlat(), new CriticalDamageFlat(), new HealthFlat());
    }

    @Override
    public String GUID() {
	return "Follower";
    }

    @Override
    public List<BaseRuneItem> runes() {
	return Arrays.asList(new ItaItem(0), new DosItem(0), new VohItem(0), new BerItem(0), new RahItem(0));
    }

    @Override
    public String unlocName() {
	return "follower";
    }

}
