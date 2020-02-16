package com.robertx22.mine_and_slash.database.affixes.suffixes;

import com.robertx22.mine_and_slash.database.affixes.ElementalSuffix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LowElementalResistFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfDissonance extends ElementalSuffix {

    public OfDissonance(Elements element) {
        super(new Requirements(LevelRequirement.fromLVL50(), SlotRequirement.jewerlyOnly()), element);
    }

    @Override
    public Suffix newGeneratedInstance(Elements element) {
        return new OfDissonance(element);
    }

    @Override
    public String GUID() {
        return "of_" + element.guidName + "_dissonance";
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new CriticalDamageFlat(), new LowElementalResistFlat(element));
    }

    @Override
    public String locNameForLangFile() {
        return "Of " + element.dmgName + "'s Dissonance";
    }

}
