package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.BuffManaRegenTrait;
import com.robertx22.mine_and_slash.items.runes.AnoItem;
import com.robertx22.mine_and_slash.items.runes.CenItem;
import com.robertx22.mine_and_slash.items.runes.DosItem;
import com.robertx22.mine_and_slash.items.runes.RahItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordProfoundSea extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new BuffManaRegenTrait()), new ManaRegenFlat(), new ManaFlat());
    }

    @Override
    public String GUID() {
        return "Profound Sea";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new DosItem(0), new AnoItem(0), new RahItem(0), new CenItem(0));

    }

    @Override
    public String locNameForLangFile() {
        return "Profound Sea";
    }
}
