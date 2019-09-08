package com.robertx22.mine_and_slash.db_lists.initializers.profession_recipes;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe2;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe3;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe4;

import java.util.ArrayList;
import java.util.List;

public class AlchemyRecipes implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<BaseRecipe> all = new ArrayList<BaseRecipe>();

        all.add(new TestRecipe());
        all.add(new TestRecipe2());
        all.add(new TestRecipe3());
        all.add(new TestRecipe4());

        all.forEach(x -> x.registerToSlashRegistry());
    }
}
