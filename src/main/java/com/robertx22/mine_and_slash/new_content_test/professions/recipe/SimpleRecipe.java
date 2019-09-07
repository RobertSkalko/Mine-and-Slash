package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;

import java.util.List;

public class SimpleRecipe extends BaseRecipe {

    public SimpleRecipe(String guid) {
        super(guid);
    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return null;
    }

    @Override
    public BasePreviewItem getOutput(ProfessionTile tile) {
        return null;
    }
}
