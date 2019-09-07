package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public abstract class ProfessionTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public Professions profession = Professions.ALCHEMY;

    public BaseRecipe currentRecipe;

    public NonNullList<ItemStack> recipeStacks;

    public NonNullList<ItemStack> materialStacks;
    public NonNullList<ItemStack> outputStacks;

    public ProfessionTile(Professions proff) {
        super(proff.tileEntityType);
        recipeStacks = NonNullList.withSize(6 * 9, ItemStack.EMPTY);
        materialStacks = NonNullList.withSize(5, ItemStack.EMPTY);
        outputStacks = NonNullList.withSize(5, ItemStack.EMPTY);
        this.profession = proff;
    }

    @Override
    public void tick() {

    }

    public void scrollToRow(int row) {
        int x = 0;
        for (int i = row * 9; i < row * 9 + ProfessionRecipeContainer.size; i++) {
            if (this.profession.recipes.size() > i) {
                this.recipeStacks.set(x, this.profession.recipes.get(i)
                        .getOutput(this)
                        .getPreview());
                x++;
            }
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory,
                                PlayerEntity playerEntity) {

        if (currentRecipe == null) {
            return new ProfessionRecipeContainer(i, this, this.getPos(), playerInventory);
        } else {
            return new ProfessionCraftingContainer(i, this, this.getPos(), playerInventory);
        }

    }
}
