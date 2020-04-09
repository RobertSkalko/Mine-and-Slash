package com.robertx22.mine_and_slash.database.affixes.prefixes.misc;

import com.robertx22.mine_and_slash.database.affixes.ElementalPrefix;
import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Heretics extends ElementalPrefix {

    public Heretics(Elements element) {
        super(new Requirements(LevelRequirement.fromHighLevel(), SlotRequirement.jewerlyOnly()), element);
    }

    @Override
    public Prefix newGeneratedInstance(Elements element) {
        return new Heretics(element);
    }

    @Override
    public String GUID() {
        return element.guidName + "_heretic";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat(), new ElementalSpellDamageFlat(element).size(StatMod.Size.HALF_MORE));
    }

    @Override
    public String locNameForLangFile() {
        return element.dmgName + " Heretic";
    }

}
