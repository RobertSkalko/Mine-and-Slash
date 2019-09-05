package com.robertx22.mine_and_slash.new_content_test.professions.blocks;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ProfessionRecipesTile extends TileEntity implements ITickableTileEntity {

    ItemStack[] stacks;

    public ProfessionRecipesTile(TileEntityType<?> type) {
        super(type);
        stacks = new ItemStack[ProfessionRecipesContainer.size];
    }

    @Override
    public void tick() {

    }
}
