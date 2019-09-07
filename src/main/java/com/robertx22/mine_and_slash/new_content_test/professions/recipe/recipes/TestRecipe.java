package com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.*;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class TestRecipe extends BaseRecipe {

    public TestRecipe() {
        super("test");
    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return Arrays.asList(new SimpleMaterial(Items.EMERALD), new SimpleMaterial(Items.GOLD_BLOCK));
    }

    @Override
    public BasePreviewItem getOutput(ProfessionTile tile) {
        return new SameStackAsPreview(Items.DIAMOND);
    }

}
