package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;

import java.util.List;

public abstract class BaseRecipe {

    public abstract List<BaseMaterial> getMaterials();

    public abstract BasePreviewItem getOutput(
            ProfessionTile tile); // needs the instance for some recipes that modify existing items

}
