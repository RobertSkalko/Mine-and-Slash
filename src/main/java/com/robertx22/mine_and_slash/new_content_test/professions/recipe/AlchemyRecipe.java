package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.Professions;

public abstract class AlchemyRecipe extends BaseRecipe {

    public AlchemyRecipe(String guid) {
        super(guid);
    }

    @Override
    public Professions profession() {
        return Professions.ALCHEMY;
    }

}
