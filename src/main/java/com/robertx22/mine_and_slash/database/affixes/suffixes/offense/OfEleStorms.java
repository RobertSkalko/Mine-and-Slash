package com.robertx22.mine_and_slash.database.affixes.suffixes.offense;

import com.robertx22.mine_and_slash.database.affixes.ElementalSuffix;
import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellToAttackDMGFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfEleStorms extends ElementalSuffix {

    public OfEleStorms(Elements element) {
        super(element);
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public Requirements requirements() {
        return new Requirements(SlotRequirement.jewerlyOnly(), LevelRequirement.fromLVL20());
    }

    @Override
    public String GUID() {
        return "Of " + element.disasterName;
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new ElementalPeneFlat(element), new ElementalSpellToAttackDMGFlat(element));
    }

    @Override
    public String locNameForLangFile() {
        return "Of " + element.disasterName;
    }

    @Override
    public Suffix newGeneratedInstance(Elements element) {
        return new OfEleStorms(element);
    }
}
