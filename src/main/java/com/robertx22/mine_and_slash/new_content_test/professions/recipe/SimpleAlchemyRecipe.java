package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class SimpleAlchemyRecipe extends AlchemyRecipe {

    List<BaseMaterial> materials = new ArrayList<>();
    BasePreviewItem output;

    public SimpleAlchemyRecipe(String guid, List<Item> mats, Item output) {
        super(guid);

        for (Item mat : mats) {
            this.materials.add(new SimpleMaterial(mat));
        }

        this.output = new SameStackAsPreview(output);

    }

    public SimpleAlchemyRecipe(String guid, List<BaseMaterial> materials,
                               BasePreviewItem output) {
        super(guid);
        this.materials = materials;
        this.output = output;
    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return materials;
    }

    @Override
    public BasePreviewItem getOutput(ProfessionTile tile) {
        return output;
    }
}
