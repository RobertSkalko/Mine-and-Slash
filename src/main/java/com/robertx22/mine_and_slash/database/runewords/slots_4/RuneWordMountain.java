package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runes.BerItem;
import com.robertx22.mine_and_slash.database.runes.CenItem;
import com.robertx22.mine_and_slash.database.runes.ItaItem;
import com.robertx22.mine_and_slash.database.runes.VohItem;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class RuneWordMountain extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HealthFlat(), new ArmorPercent(), new HealthRegenFlat());
    }

    @Override
    public String GUID() {
        return "mountain";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new ItaItem(0), new CenItem(0), new VohItem(0), new BerItem(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Mountain";
    }

}
