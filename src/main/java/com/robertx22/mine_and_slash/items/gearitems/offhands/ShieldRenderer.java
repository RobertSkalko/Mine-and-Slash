package com.robertx22.mine_and_slash.items.gearitems.offhands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.model.ShieldModel;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.texture.AtlasTexture;
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
    public void render(ItemStack stack, MatrixStack mat, IRenderTypeBuffer renderType, int light, int overlayLight) {
        Item item = stack.getItem();

        if (item instanceof NormalShield) {

            NormalShield shield = (NormalShield) item;

            mat.push();

            mat.scale(1F, -0.6F, -1.0F);

            Material material = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, shield.resource);

            IVertexBuilder ivertexbuilder = material.getSprite()
                .wrapBuffer(ItemRenderer.getBuffer(renderType, modelShield.getRenderType(material.getAtlasLocation()), false, stack.hasEffect()));

            modelShield.func_228294_b_()
                .render(mat, ivertexbuilder, light, overlayLight, 1.0F, 1.0F, 1.0F, 1.0F);

            modelShield.func_228293_a_()
                .render(mat, ivertexbuilder, light, overlayLight, 1.0F, 1.0F, 1.0F, 1.0F);

            mat.pop();

        }

    }

    @Override
    public ItemStackTileEntityRenderer call() throws Exception {
        return instance;
    }
}

