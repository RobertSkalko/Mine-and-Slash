package com.robertx22.mine_and_slash.new_content_test.professions.data;

import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe2;

import java.util.ArrayList;
import java.util.List;

public class AlchemyRecipes {

    public static List<BaseRecipe> ALL = new ArrayList<>();

    static {
        ALL = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            ALL.add(new TestRecipe2());

        }
        ALL.set(5, new TestRecipe());
        ALL.set(15, new TestRecipe());
        ALL.set(55, new TestRecipe());
        ALL.set(65, new TestRecipe());
        ALL.set(155, new TestRecipe());

    }
}
