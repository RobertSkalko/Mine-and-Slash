package com.robertx22.mine_and_slash.database.runewords.slots_4;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.AllTraitMods;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_types.spell_buff_traits.ZephyrTrait;
import com.robertx22.mine_and_slash.items.runes.BerItem;
import com.robertx22.mine_and_slash.items.runes.CenItem;
import com.robertx22.mine_and_slash.items.runes.MosItem;
import com.robertx22.mine_and_slash.items.runes.XahItem;
import com.robertx22.mine_and_slash.items.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordZephyr extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new AllTraitMods(new ZephyrTrait()), new EnergyRegenFlat(), new ElementalResistFlat(Elements.Thunder), new ArmorFlat());
    }

    @Override
    public String GUID() {
        return "Zephyr";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new MosItem(0), new XahItem(0), new CenItem(0), new BerItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Zephyr";
    }
}
