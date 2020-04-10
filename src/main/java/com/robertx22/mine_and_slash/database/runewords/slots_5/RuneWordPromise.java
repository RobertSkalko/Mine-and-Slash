package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;

import java.util.Arrays;
import java.util.List;

public class RuneWordPromise extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(
            new CooldownReductionFlat(),
            new ManaRegenFlat(),
            new ArmorFlat()
        );
    }

    @Override
    public String GUID() {
        return "promise";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Voh(0), new Ano(0), new Cen(0), new Xah(0), new Ber(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Promise";
    }
}

