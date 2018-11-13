package com.rabbit.gui.component.display;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.utils.DoubleChangeListener;
import com.rabbit.gui.utils.DoubleListener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProgressBar extends GuiWidget {

	private double progress;
	private ResourceLocation bg = new ResourceLocation("rabbit", "textures/gui/progressbarbg.png");
	private ResourceLocation fg = new ResourceLocation("rabbit", "textures/gui/progressbarfg.png");
	private DoubleListener progressChanged = new DoubleListener(0);

	private boolean visible;

	public ProgressBar(int x, int y, int width, int height) {
		super(x, y, width, height);
		DoubleChangeListener listener = event -> {
			progress = event.getDispatcher().getValue();
		};

		progressChanged.addDoubleChangeListener(listener);
	}

	public double getProgress() {
		return progress;
	}

	public DoubleListener getProgressChangedListener() {
		return progressChanged;
	}

	public boolean isVisible() {
		return visible;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		if (visible) {
			renderBG();
			renderFG();
		}
	}

	private void renderBG() {
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableTexture2D();
			GlStateManager.enableBlend();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().renderEngine.bindTexture(bg);
			Renderer.drawScaledTexturedRect(getX(), getY(), getWidth(), getHeight());
		}
		GlStateManager.popMatrix();
	}

	private void renderFG() {
		GlStateManager.pushMatrix();
		{
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.enableTexture2D();
			GlStateManager.enableBlend();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().renderEngine.bindTexture(fg);
			Renderer.drawScaledTexturedRect(getX() + 1, getY() + 1, (int) ((getWidth() - 2) * progress),
					getHeight() - 2);
		}
		GlStateManager.popMatrix();
	}

	public void setProgress(double progress) {
		this.progress = progress;
	}

	public void setProgressChangedListener(DoubleListener progressChanged) {
		this.progressChanged = progressChanged;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
