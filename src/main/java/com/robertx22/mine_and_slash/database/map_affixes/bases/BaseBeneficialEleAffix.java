package com.robertx22.mine_and_slash.database.map_affixes.bases;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public abstract class BaseBeneficialEleAffix extends BeneficialMapAffix {

    @Override
    public int getRarityRank() {
        return IRarity.Rare;

    }
}
