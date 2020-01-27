package com.robertx22.mine_and_slash.items.gearitems.offhands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelBakery;
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
    public void render(ItemStack stack, MatrixStack mat, IRenderTypeBuffer renderType, int x, int y) {
        Item item = stack.getItem();

        if (item instanceof NormalShield) {

            NormalShield shield = (NormalShield) item;

            mat.push();

            mat.scale(1F, -0.6F, -1.0F);

            Material material = ModelBakery.LOCATION_SHIELD_BASE;

            IVertexBuilder ivertexbuilder = material.getBuffer(renderType, RenderType::entityNoOutline);

            Minecraft.getInstance().getTextureManager().bindTexture(shield.resource);

            //handle and shield parts
            modelShield.func_228294_b_().
                    render(mat, ivertexbuilder, x, y, 1F, 1F, 1F, 1F);
            modelShield.func_228293_a_().
                    render(mat, ivertexbuilder, x, y, 1F, 1F, 1F, 1F);

            mat.pop();

        }

    }

    @Override
    public ItemStackTileEntityRenderer call() throws Exception {
        return instance;
    }
}

