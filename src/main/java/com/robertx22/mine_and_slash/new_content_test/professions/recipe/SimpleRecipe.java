package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.builders.SimpleRecipeBuilders.SimpleRecipeMatBuilder;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecipe extends BaseRecipe {

    public List<BaseMaterial> materials = new ArrayList<>();
    public BaseOutputItem output;
    public Professions profession;
    //  public Professions.Levels lvl = Professions.Levels.ONE;

    public SimpleRecipe(String guid, List<Item> mats, Item output) {
        super(guid);

        for (Item mat : mats) {
            this.materials.add(new SimpleMaterial(mat));
        }

        this.output = new SameStackAsPreview(output);

    }

    public SimpleRecipe(String guid, List<BaseMaterial> materials,
                        BaseOutputItem output) {
        super(guid);
        this.materials = materials;
        this.output = output;
    }

    @Override
    public Professions profession() {
        return this.profession;
    }

    private SimpleRecipe(String guid) {
        super(guid);

    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return materials;
    }

    @Override
    public BaseOutputItem getOutput(ProfessionTile tile) {
        return output;
    }

    public static class Builder {

        public static SimpleRecipeMatBuilder create(String guid, Professions prof) {
            SimpleRecipe recipe = new SimpleRecipe(guid);
            recipe.profession = prof;
            return new SimpleRecipeMatBuilder(recipe);

        }

    }

}

