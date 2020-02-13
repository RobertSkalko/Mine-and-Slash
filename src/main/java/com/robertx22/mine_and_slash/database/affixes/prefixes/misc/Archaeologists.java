package com.robertx22.mine_and_slash.database.affixes.prefixes.misc;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.Arrays;
import java.util.List;

public class Archaeologists extends Prefix {

    public Archaeologists() {
        super(new Requirements(SlotRequirement.bracelet()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public String GUID() {
        return "archaeologists";
    }

    @Override
    public List<StatMod> StatMods() {
        return Arrays.asList(new LootTypeBonusFlat(LootType.UniqueItem));
    }

    @Override
    public String locNameForLangFile() {
        return "Archaeologist's";
    }
}

