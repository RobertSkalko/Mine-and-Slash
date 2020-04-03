package com.robertx22.mine_and_slash.database.affixes.suffixes.thon_reward;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Sword;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifestealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.BlockReflectFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfTheCactus extends Suffix {

    public OfTheCactus() {
        super(new Requirements(SlotRequirement.slots(Sword.INSTANCE)));
    }

    @Override
    public String GUID() {
        return "of_the_cactus";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(
            new BlockReflectFlat(Elements.Nature),
            new LifestealFlat().size(StatMod.Size.DOUBLE)
        );
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Of the Cactus";
    }
}