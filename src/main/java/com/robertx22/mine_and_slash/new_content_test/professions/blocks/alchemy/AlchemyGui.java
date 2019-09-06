package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.ProfessionGui;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlchemyGui extends ProfessionGui<AlchemyContainer, AlchemyTile> {

    private static final ResourceLocation RECIPE = new ResourceLocation(Ref.MODID, "textures/gui/recipes_list.png");

    public AlchemyGui(AlchemyContainer cont, PlayerInventory inv, ITextComponent text) {
        super(cont, inv, text, AlchemyTile.class);
    }

    @Override
    public void drawExtraGuiStuff(float partialTicks, int x, int y) {

    }

    @Override
    public ResourceLocation getRecipeTexture() {
        return RECIPE;
    }

    @Override
    public ResourceLocation getCraftingTexture() {
        return null;
    }

    @Override
    public ResourceLocation getModifyingTexture() {
        return null;
    }
}
