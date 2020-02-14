package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.items.runes.*;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordInfinity extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new EnergyFlat(), new EnergyRegenFlat(), new EnergyRegenPercent());
    }

    @Override
    public String GUID() {
        return "infinity";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new CenItem(0), new DosItem(0), new AnoItem(0), new BerItem(0), new XahItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Infinity";
    }
}
