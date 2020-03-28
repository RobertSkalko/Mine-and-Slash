package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.DodgeRatingPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordGhost extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new DodgeRatingFlat().size(StatMod.Size.HALF_MORE), new DodgeRatingPercent(), new ElementalResistFlat(Elements.Nature));
    }

    @Override
    public String GUID() {
        return "ghost";
    }

    @Override
    public List<BaseRune> runes() {
        return Arrays.asList(new Voh(0), new Xah(0), new Ber(0), new Ano(0), new Cen(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Ghost";
    }
}
