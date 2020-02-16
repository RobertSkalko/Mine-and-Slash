package com.robertx22.mine_and_slash.database.affixes.suffixes.unique;

import com.robertx22.mine_and_slash.database.affixes.Suffix;
import com.robertx22.mine_and_slash.database.requirements.LevelRequirement;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.requirements.UniqueTierRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class OfGodhood extends Suffix {

    public OfGodhood() {
        super(new Requirements(SlotRequirement.helmet(), LevelRequirement.fromLVL50(), new UniqueTierRequirement(10)));
    }

    @Override
    public String GUID() {
        return "of_godhood";
    }

    @Override
    public List<StatMod> StatMods() {

        return Arrays.asList(new HealthMulti(), new DodgeRatingFlat().size(StatMod.Size.HIGH), new ArmorFlat());

    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public String locNameForLangFile() {
        return "Of Godhood";
    }
}
