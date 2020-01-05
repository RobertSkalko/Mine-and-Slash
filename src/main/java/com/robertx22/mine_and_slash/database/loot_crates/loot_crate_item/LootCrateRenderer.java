package com.robertx22.mine_and_slash.database.loot_crates.loot_crate_item;

import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class LootCrateRenderer extends ItemStackTileEntityRenderer implements Supplier<Callable<ItemStackTileEntityRenderer>> {

    public static LootCrateRenderer INSTANCE = new LootCrateRenderer();

    @Override
    public void renderByItem(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof MapLootCrateItem) {
            TileEntityRendererDispatcher.instance.renderAsItem(new ChestTileEntity());
        } else {
            super.renderByItem(stack);
        }
    }

    @Override
    public Callable<ItemStackTileEntityRenderer> get() {
        return LootCrateRenderer::new;
    }
}