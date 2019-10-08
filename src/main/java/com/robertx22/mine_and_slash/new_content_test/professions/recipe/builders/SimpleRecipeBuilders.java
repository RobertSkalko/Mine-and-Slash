package com.robertx22.mine_and_slash.new_content_test.professions.recipe.builders;

import com.robertx22.mine_and_slash.new_content_test.professions.recipe.*;
import net.minecraft.item.Item;

public class SimpleRecipeBuilders {

    public static class SimpleRecipeMatBuilder {
        SimpleRecipe recipe;

        public SimpleRecipeMatBuilder(SimpleRecipe recipe) {
            this.recipe = recipe;
        }

        public SimpleRecipeMatBuilder addMaterial(BaseMaterial mat) {
            this.recipe.materials.add(mat);
            return this;
        }

        public SimpleRecipeMatBuilder addMaterial(Item item) {
            this.recipe.materials.add(new SimpleMaterial(item));
            return this;
        }

        public SimpleRecipeMatBuilder addMaterial(Item item, int amount) {
            this.recipe.materials.add(new SimpleMaterial(item).amount(amount));
            return this;
        }

        public SimpleRecipeMatBuilder addMaterial(Item item, float amount) {
            return addMaterial(item, (int) amount);
        }

        public SimpleRecipeOuputBuilder buildMaterials() {
            return new SimpleRecipeOuputBuilder(recipe);
        }

    }

    public static class SimpleRecipeOuputBuilder {
        SimpleRecipe recipe;

        public SimpleRecipeOuputBuilder(SimpleRecipe recipe) {
            this.recipe = recipe;
        }

        public SimpleRecipeFinishBuilder setOutput(BaseOutputItem output) {
            this.recipe.output = output;
            return new SimpleRecipeFinishBuilder(recipe);

        }

        public SimpleRecipeLevelReqBuilder setOutput(Item output, int amount) {
            this.recipe.output = new SimpleOutputItem(output, recipe).setAmount(amount);
            return new SimpleRecipeLevelReqBuilder(recipe);
        }

        public SimpleRecipeLevelReqBuilder setOutput(Item output) {
            this.recipe.output = new SimpleOutputItem(output, recipe);
            return new SimpleRecipeLevelReqBuilder(recipe);
        }

    }

    public static class SimpleRecipeLevelReqBuilder {
        SimpleRecipe recipe;

        public SimpleRecipeLevelReqBuilder(SimpleRecipe recipe) {
            this.recipe = recipe;

        }

        public SimpleRecipeExpBuilder levelReq(int lvl) {
            recipe.professionLevelReq = lvl;
            return new SimpleRecipeExpBuilder(recipe);
        }

    }

    public static class SimpleRecipeExpBuilder {
        SimpleRecipe recipe;

        public SimpleRecipeExpBuilder(SimpleRecipe recipe) {
            this.recipe = recipe;
        }

        public SimpleRecipeFinishBuilder expGained(int exp) {

            if (recipe.getLevelReq() == 1) {
                recipe.expGiven = exp * 5; // easier start of lvling
            } else {
                recipe.expGiven = exp * recipe.getLevelReq();
            }
            return new SimpleRecipeFinishBuilder(recipe);

        }

    }

    public static class SimpleRecipeFinishBuilder {
        SimpleRecipe recipe;

        public SimpleRecipeFinishBuilder(SimpleRecipe recipe) {
            this.recipe = recipe;
        }

        public SimpleRecipe build() {
            return this.recipe;
        }

    }
}
