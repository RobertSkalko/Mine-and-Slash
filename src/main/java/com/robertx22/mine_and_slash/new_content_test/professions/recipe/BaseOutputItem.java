package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import net.minecraft.item.ItemStack;

public abstract class BaseOutputItem extends BasePreviewItem {

    // needs the instance for some recipes that modify existing items
    public abstract ItemStack getOutput(ProfessionTile tile);

}
