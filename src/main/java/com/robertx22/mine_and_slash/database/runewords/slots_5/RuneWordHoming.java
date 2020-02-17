package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.DosItem;
import com.robertx22.mine_and_slash.database.runes.ItaItem;
import com.robertx22.mine_and_slash.database.runes.VohItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordHoming extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new CriticalHitFlat(), new CriticalDamageFlat(), new HealthPercent());
    }

    @Override
    public String GUID() {
        return "follower";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new ItaItem(0), new DosItem(0), new VohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Follower";
    }
}
