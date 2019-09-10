package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;

public interface IHasRecipe {

    Professions proffesion();

    BaseRecipe getRecipe();
}
