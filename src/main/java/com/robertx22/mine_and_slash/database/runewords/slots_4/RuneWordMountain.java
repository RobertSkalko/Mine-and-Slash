package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runes.Ber;
import com.robertx22.mine_and_slash.database.runes.Cen;
import com.robertx22.mine_and_slash.database.runes.Ita;
import com.robertx22.mine_and_slash.database.runes.Voh;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
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
    public List<BaseRune> runes() {
        return Arrays.asList(new Ita(0), new Cen(0), new Voh(0), new Ber(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Mountain";
    }

}
