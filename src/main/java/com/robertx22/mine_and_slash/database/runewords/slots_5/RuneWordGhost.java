package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.items.runes.*;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.flat.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.DodgePercent;
import com.robertx22.mine_and_slash.database.stats.types.spell_buff_traits.GhostProjectileTrait;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordGhost extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new GhostProjectileTrait()), new DodgeRatingFlat(), new DodgePercent(), new ElementalResistFlat(Elements.Nature));
    }

    @Override
    public String GUID() {
        return "Ghost";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new VohItem(0), new XahItem(0), new BerItem(0), new AnoItem(0), new CenItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Ghost";
    }
}
