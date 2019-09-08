package com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.*;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class TestRecipe4 extends AlchemyRecipe {

    public TestRecipe4() {
        super("test4");
    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return Arrays.asList(new SimpleMaterial(Items.ACACIA_PLANKS), new SimpleMaterial(Items.CACTUS));
    }

    @Override
    public BasePreviewItem getOutput(ProfessionTile tile) {
        return new SameStackAsPreview(Items.DARK_OAK_PLANKS);
    }
}
