package com.rabbit.gui.component.control;

import org.lwjgl.input.Mouse;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.utils.Geometry;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScrollBar extends GuiWidget {

	public static interface OnProgressChanged {
		void onProgressChanged(ScrollBar bar, float modifier);
	}

	private float scrolled = 0;

	protected int scrollerSize;

	protected boolean isScrolling = false;

	protected boolean visible = true;

	protected OnProgressChanged progressChangedListener = (bar, mod) -> {
	};

	protected float scrollWeight;

	protected boolean handleMouseWheel;

	public ScrollBar(int xPos, int yPos, int width, int height, int scrollerSize) {
		super(xPos, yPos, width, height);
		this.scrollerSize = scrollerSize;
		scrollWeight = 0.05F;
	}

	/**
	 * Calculates scroller progress based on mouse y pos
	 *
	 * @param mouseY
	 */
	private void calculateScroller(int mouseY) {
		if (isScrolling) {
			float magic = (((mouseY - getY()) + 2) - 10F) / ((getY() + height) - (getY() + 2) - 15.0F);
			updateProgress(magic - getScrolledAmt());
		}
	}

	private void drawScroller(int xPos, int yPos, int width, int height) {
		Minecraft.getMinecraft().renderEngine
				.bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tabs.png"));
		Renderer.drawContinuousTexturedBox(xPos, yPos, isScrolling() ? 244 : 232, 0, width, height, 12, 15, 1, 2, 2, 2);
	}

	/**
	 * Returns a float value between 0 and 1,
	 */
	public float getProgress() {
		return getScrolledAmt();
	}

	public OnProgressChanged getProgressChangedListener() {
		return progressChangedListener;
	}

	public float getScrolledAmt() {
		return scrolled;
	}

	public boolean isScrolling() {
		return isScrolling;
	}

	public boolean isVisible() {
		return visible;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		if (isVisible()) {
			calculateScroller(mouseY);
			Minecraft.getMinecraft().renderEngine
					.bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_items.png"));
			Renderer.drawContinuousTexturedBox(getX(), getY(), 174 - 1, 17 - 1, width, height, 14 + 2, 112 + 2, 2, 2, 2,
					2);
			int scrollerHeight = (int) (getY() + 2 + (getScrolledAmt() * (height - 4 - scrollerSize)));
			drawScroller(getX() + 2, scrollerHeight, width - 4, scrollerSize);
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);
		isScrolling = !overlap && Geometry.isDotInArea(getX() + 2,
				(int) (getY() + 2 + (getScrolledAmt() * (height - scrollerSize))), width - 4, scrollerSize, posX, posY);
		return isScrolling;
	}

	@Override
	public void onMouseInput() {
		super.onMouseInput();
		if (shouldHandleMouseWheel()) {
			double delta = Mouse.getDWheel(); // getDWheel resets to 0 so only 1
												// object responds...
			if (delta < 0) {
				updateProgress(scrollWeight);
			}
			if (delta > 0) {
				updateProgress(-scrollWeight);
			}
		}
	}

	@Override
	public void onMouseRelease(int mouseX, int mouseY) {
		super.onMouseRelease(mouseX, mouseY);
		isScrolling = false;
	}

	private void revalidateScroller() {
		if (getScrolledAmt() < 0) {
			setScrolledAmt(0);
		}
		if (getScrolledAmt() > 1) {
			setScrolledAmt(1);
		}
	}

	public ScrollBar setHandleMouseWheel(boolean status) {
		handleMouseWheel = status;
		return this;
	}

	public ScrollBar setProgress(float scroll) {
		setScrolledAmt(scroll);
		revalidateScroller();
		return this;
	}

	public ScrollBar setProgressChangedListener(OnProgressChanged progressChangedListener) {
		this.progressChangedListener = progressChangedListener;
		return this;
	}

	public void setScrolledAmt(float scrolled) {
		this.scrolled = scrolled;
	}

	public ScrollBar setScrollerSize(int size) {
		scrollerSize = size;
		return this;
	}

	public ScrollBar setScrollWeight(float newWeight) {
		scrollWeight = newWeight;
		return this;
	}

	public ScrollBar setVisiblie(boolean visible) {
		this.visible = visible;
		return this;
	}

	public boolean shouldHandleMouseWheel() {
		return handleMouseWheel;
	}

	public void updateProgress(float modifier) {
		setProgress(getScrolledAmt() + modifier);
		getProgressChangedListener().onProgressChanged(this, modifier);
	}

}
