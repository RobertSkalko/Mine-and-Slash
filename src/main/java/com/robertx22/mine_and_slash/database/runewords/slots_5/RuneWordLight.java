package com.robertx22.mine_and_slash.database.runewords.slots_5;

import com.robertx22.mine_and_slash.database.items.runes.*;
import com.robertx22.mine_and_slash.database.runes.*;
import com.robertx22.mine_and_slash.database.runes.base.BaseRuneItem;
import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class RuneWordLight extends RuneWord {

    @Override
    public List<StatMod> mods() {
        return Arrays.asList(new HealthRegenFlat(), new HealthFlat(), new ElementalResistFlat(Elements.Fire));
    }

    @Override
    public String GUID() {
        return "light";
    }

    @Override
    public List<BaseRuneItem> runes() {
        return Arrays.asList(new ItaItem(0), new AnoItem(0), new DosItem(0), new XahItem(0), new GohItem(0));
    }

    @Override
    public String locNameForLangFile() {
        return "Light";
    }
}
