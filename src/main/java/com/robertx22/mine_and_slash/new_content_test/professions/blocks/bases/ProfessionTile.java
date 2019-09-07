package com.robertx22.mine_and_slash.new_content_test.professions.blocks.bases;

import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.new_content_test.professions.recipe.BaseRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

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
        List<BaseRecipe> recipes = this.profession.recipes();

        int x = 0;
        for (int i = row * 9; i < row * 9 + ProfessionRecipeContainer.size; i++) {
            if (recipes.size() > i) {
                this.recipeStacks.set(x, recipes.get(i).getPreviewRecipeStack(this));
                x++;
            }
        }
    }

    public void openCraftingForRecipe(ServerPlayerEntity player, BaseRecipe recipe) {

        currentRecipe = recipe;

        NetworkHooks.openGui(player, this, extraData -> {
            extraData.writeBlockPos(this.getPos());
        });

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
