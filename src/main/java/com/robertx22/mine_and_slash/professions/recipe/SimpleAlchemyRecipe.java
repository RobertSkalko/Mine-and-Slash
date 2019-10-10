package com.robertx22.mine_and_slash.professions.recipe;

import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionTile;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class SimpleAlchemyRecipe extends AlchemyRecipe {

    List<BaseMaterial> materials = new ArrayList<>();
    BaseOutputItem output;

    public SimpleAlchemyRecipe(String guid, List<Item> mats, Item output) {
        super(guid);

        for (Item mat : mats) {
            this.materials.add(new SimpleMaterial(mat));
        }

        this.output = new SameStackAsPreview(output);

    }

    public SimpleAlchemyRecipe(String guid, List<BaseMaterial> materials,
                               BaseOutputItem output) {
        super(guid);
        this.materials = materials;
        this.output = output;
    }

    @Override
    public List<BaseMaterial> getMaterials() {
        return materials;
    }

    @Override
    public BaseOutputItem getOutput(ProfessionTile tile) {
        return output;
    }
}
