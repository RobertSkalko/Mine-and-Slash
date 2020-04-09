package com.robertx22.mine_and_slash.database.affixes.prefixes.misc;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class ThirstOfLightning extends BaseThirstPrefix {

    @Override
    public String GUID() {
        return "thirst_of_lightning";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new LifestealFlat(), new ElementalSpellDamageFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return "Thirst Of Lightning";
    }
}
