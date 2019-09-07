package com.robertx22.mine_and_slash.new_content_test.professions.blocks.alchemy;

import com.robertx22.mine_and_slash.mmorpg.registers.common.BlockRegister;
import com.robertx22.mine_and_slash.new_content_test.professions.blocks.ProfessionTile;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.recipes.TestRecipe2;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AlchemyTile extends ProfessionTile {

    static List<BaseRecipe> recipestest = new ArrayList<>();

    static {
        recipestest = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            recipestest.add(new TestRecipe2());

        }
        recipestest.set(5, new TestRecipe());
        recipestest.set(15, new TestRecipe());
        recipestest.set(55, new TestRecipe());
        recipestest.set(65, new TestRecipe());
        recipestest.set(155, new TestRecipe());

    }

    public AlchemyTile() {
        super(BlockRegister.ALCHEMY_TILE);

        this.scrollToRow(0);

    }

    @Override
    public List<BaseRecipe> recipes() {
        return recipestest;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {
        return new AlchemyRecipeContainer(i, playerInventory, this, this.getPos());
    }
}
