package com.robertx22.mine_and_slash.new_content_test.professions.recipe;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases.ProfessionTile;
import net.minecraft.item.ItemStack;

public abstract class BaseOutputItem {

    public abstract ItemStack getPreview();

    // needs the instance for some recipes that modify existing items
    public abstract ItemStack generateStack(ProfessionTile tile);

}
