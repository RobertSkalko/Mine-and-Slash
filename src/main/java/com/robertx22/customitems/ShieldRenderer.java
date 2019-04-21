package com.robertx22.customitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ShieldRenderer extends TileEntityItemStackRenderer {
    public final TileEntityItemStackRenderer parent;

    Item theitem;

    private final ModelShield modelShield = new ModelShield();
    private ResourceLocation modelTexture = new ResourceLocation("mmorpg:textures/shield/scorgershell_shield.png");

    public ShieldRenderer(TileEntityItemStackRenderer renderer, String name, Item item) {
	parent = renderer;

	modelTexture = new ResourceLocation("mmorpg:textures/shield/" + name + ".png");

	this.theitem = item;

    }

    @Override
    public void renderByItem(ItemStack stack) {
	super.renderByItem(stack);
	Item item = stack.getItem();
	if (item == theitem) {
	    Minecraft.getMinecraft().getTextureManager().bindTexture(modelTexture);
	    GlStateManager.pushMatrix();
	    GlStateManager.scale(1.0, -1.0, -1.0);
	    modelShield.render();
	    GlStateManager.popMatrix();

	}
    }

}