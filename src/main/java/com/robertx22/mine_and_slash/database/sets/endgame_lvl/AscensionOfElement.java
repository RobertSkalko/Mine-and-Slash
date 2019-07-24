package com.robertx22.mine_and_slash.database.sets.endgame_lvl;

import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.sets.ElementalSet;
import com.robertx22.mine_and_slash.database.sets.Set;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalAffinityFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalFocusFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.HashMap;

public class AscensionOfElement extends ElementalSet {

    public AscensionOfElement(Elements element) {
        super(element);
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public Set newGeneratedInstance(Elements element) {
        return new AscensionOfElement(element);
    }

    @Override
    public HashMap<Integer, StatMod> AllMods() {

        return new HashMap<Integer, StatMod>() {
            {
                {
                    put(2, new ElementalPeneFlat(element));
                    put(3, new ElementalFocusFlat(element));
                    put(4, new ElementalAffinityFlat(element));

                }
            }
        };
    }

    @Override
    public String locNameForLangFile() {
        return "Ascension of " + this.element.name();
    }

    @Override
    public String GUID() {
        return "ascension_of_" + element.name();
    }

    @Override
    public Requirements requirements() {
        return new Requirements(LevelRequirement.endgameLVLOnly(), SlotRequirement.armorsOnlyNoOffHand());
    }
}
