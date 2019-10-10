package com.robertx22.mine_and_slash.professions.recipe;

import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;

public abstract class AlchemyRecipe extends BaseRecipe {

    public AlchemyRecipe(String guid) {
        super(guid);
    }

    @Override
    public Professions profession() {
        return Professions.ALCHEMY;
    }

}
