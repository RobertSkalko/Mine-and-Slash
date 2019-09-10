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

        public SimpleRecipeFinishBuilder setOutput(Item output) {
            this.recipe.output = new SimpleOutputItem(output);
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
