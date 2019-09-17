package com.robertx22.mine_and_slash.database.affixes.prefixes.misc;

import com.robertx22.mine_and_slash.database.affixes.Prefix;
import com.robertx22.mine_and_slash.database.requirements.Requirements;
import com.robertx22.mine_and_slash.database.requirements.SlotRequirement;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public abstract class BaseThirstPrefix extends Prefix {

    public BaseThirstPrefix() {
        super(new Requirements(SlotRequirement.jewerlyOnly()));
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

}
