package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.items.runes.*;
import com.robertx22.mine_and_slash.database.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.types.spell_buff_traits.HomingTrait;

import java.util.Arrays;
import java.util.List;

public class RuneWordHoming extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new HomingTrait()), new CriticalHitFlat(), new CriticalDamageFlat(), new HealthFlat()
                .multi(0.5F));
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
    public String locNameForLangFile() {
        return "Follower";
    }
}
