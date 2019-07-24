package com.robertx22.mine_and_slash.items.gearitems.offhands;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.concurrent.Callable;

public class ShieldRenderer extends ItemStackTileEntityRenderer implements Callable<ItemStackTileEntityRenderer> {
    public final ItemStackTileEntityRenderer instance;

    private final ShieldModel modelShield = new ShieldModel();

    public ShieldRenderer() {
        instance = this;
    }

    @Override
    public void renderByItem(ItemStack stack) {
        Item item = stack.getItem();

        if (item instanceof NormalShield) {

            NormalShield shield = (NormalShield) item;

            Minecraft.getInstance().getTextureManager().bindTexture(shield.resource);
            GlStateManager.pushMatrix();
            GlStateManager.scaled(1F, -0.6F, -1.0);
            modelShield.render();
            GlStateManager.popMatrix();

        }

    }

    @Override
    public ItemStackTileEntityRenderer call() throws Exception {
        return instance;
    }
}

