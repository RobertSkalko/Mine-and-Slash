package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

public abstract class AlchemyRecipe extends BaseRecipe {

    public AlchemyRecipe(String guid) {
        super(guid);
    }

    @Override
    public Type recipeType() {
        return Type.ALCHEMY;
    }

}
