package com.rabbit.gui.component.display;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.render.Renderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DisplayItem extends GuiWidget {

	private Item item;

	public DisplayItem(int xPos, int yPos, int width, int height, Item item) {
		super(xPos, yPos, width, height);
		setItem(item);
	}

	public Item getItem() {
		return item;
	}

	@Override
	public void onDraw(int xMouse, int yMouse, float partialTicks) {
		super.onDraw(xMouse, yMouse, partialTicks);
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			RenderHelper.enableGUIStandardItemLighting();

			double scaleAmt = width / 16.0;
			GlStateManager.translate(x, y, 0);
			GlStateManager.scale(scaleAmt, scaleAmt, scaleAmt);

			RenderItem renderItem = Renderer.getRenderItem();
			// the vanilla item render code translates 50 in the z axis
			// and then another 100 in the camera phase
			renderItem.zLevel = -50;
			renderItem.renderItemAndEffectIntoGUI(new ItemStack(item, 1), 0, 0);

			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
			GlStateManager.resetColor();
		}
		GlStateManager.popMatrix();
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
