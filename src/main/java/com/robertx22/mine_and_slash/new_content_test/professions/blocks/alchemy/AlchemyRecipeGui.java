package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.new_content_test.professions.blocks.ProfessionRecipeGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class AlchemyRecipeGui extends ProfessionRecipeGui<AlchemyRecipeContainer, AlchemyTile> {

    public AlchemyRecipeGui(AlchemyRecipeContainer cont, PlayerInventory inv,
                            ITextComponent text) {
        super(cont, inv, text, AlchemyTile.class);
    }

    @Override
    public void drawExtraGuiStuff(float partialTicks, int x, int y) {

    }

}
