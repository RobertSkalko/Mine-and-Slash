package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.PurityTrait;
import com.robertx22.mine_and_slash.items.runes.*;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;

import java.util.Arrays;
import java.util.List;

public class RuneWordPurity extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new PurityTrait()), new HealthRegenFlat(), new HealthFlat(), new ManaRegenFlat());
    }

    @Override
    public String GUID() {
        return "Purity";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new GohItem(0), new AnoItem(0), new DosItem(0), new CenItem(0), new MosItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Purity";
    }
}
