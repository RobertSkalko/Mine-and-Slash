package com.robertx22.mine_and_slash.database.affixes.suffixes.mixed;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfMalevolentSpirits extends Suffix {

    public OfMalevolentSpirits() {
        super(new Requirements(SlotRequirement.armorsOnly(), LevelRequirement.fromMidLevel()));
    }

    @Override
    public String GUID() {
        return "of_malevolent_spirits";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new EnergyRegenPercent().size(StatMod.Size.ONE_LESS),
            new ElementalResistFlat(Elements.Water).size(StatMod.Size.DOUBLE)
        );
    }

    @Override
    public int getRarityRank() {
        return IRarity.Epic;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Malevolent Spirits";
    }

}
