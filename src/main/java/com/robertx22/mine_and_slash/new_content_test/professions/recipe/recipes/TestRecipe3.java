package com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.*;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class TestRecipe3 extends AlchemyRecipe {

    public TestRecipe3() {
        super("test3");
    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return Arrays.asList(new SimpleMaterial(Items.SHEEP_SPAWN_EGG), new SimpleMaterial(Items.CACTUS));
    }

    @Override
    public BasePreviewItem getOutput(ProfessionTile tile) {
        return new SameStackAsPreview(Items.WOODEN_AXE);
    }
}
