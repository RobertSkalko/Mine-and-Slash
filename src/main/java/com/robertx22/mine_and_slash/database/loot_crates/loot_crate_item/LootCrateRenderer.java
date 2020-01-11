package com.robertx22.mine_and_slash.database.loot_crates.loot_crate_item;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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
    public void render(ItemStack stack, MatrixStack p_228364_2_,
                       IRenderTypeBuffer p_228364_3_, int p_228364_4_, int p_228364_5_) {
        Item item = stack.getItem();
        if (item instanceof MapLootCrateItem) {
            TileEntityRendererDispatcher.instance.renderEntity(new ChestTileEntity(), new MatrixStack()); // todo
        } else {
            super.render(stack, p_228364_2_, p_228364_3_, p_228364_4_, p_228364_5_);
        }
    }

    @Override
    public Callable<ItemStackTileEntityRenderer> get() {
        return LootCrateRenderer::new;
    }
}