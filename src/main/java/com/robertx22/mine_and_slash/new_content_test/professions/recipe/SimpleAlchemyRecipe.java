package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;

import java.util.List;

public class SimpleAlchemyRecipe extends AlchemyRecipe {

    public SimpleAlchemyRecipe(String guid, List<BaseMaterial> materials,
                               BasePreviewItem output) {
        super(guid);
        this.materials = materials;
        this.output = output;
    }

    List<BaseMaterial> materials;
    BasePreviewItem output;

    @Override
    public List<BaseMaterial> getMaterials() {
        return materials;
    }

    @Override
    public BasePreviewItem getOutput(ProfessionTile tile) {
        return output;
    }
}
