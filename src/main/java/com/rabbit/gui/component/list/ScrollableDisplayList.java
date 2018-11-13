package com.rabbit.gui.component.list;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.component.control.ScrollBar;
import com.rabbit.gui.component.list.entries.ListEntry;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.utils.Geometry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class ScrollableDisplayList extends DisplayList {

	protected ScrollBar scrollBar;

	public ScrollableDisplayList(int xPos, int yPos, int width, int height, int slotHeight, List<ListEntry> content) {
		super(xPos, yPos, width, height, slotHeight, content);
	}

	/**
	 * Returns true if content height of list is not more that list actual height
	 */
	private boolean canFit() {
		return (content.size() * slotHeight) < height;
	}

	@Override
	protected void drawListContent(int mouseX, int mouseY) {
		scrollBar.setVisiblie(!canFit());
		scrollBar.setHandleMouseWheel(!canFit() && isUnderMouse(mouseX, mouseY));
		scrollBar.setScrollerSize(getScrollerSize());
		int scale = Geometry.computeScaleFactor();
		for (int i = 0; i < content.size(); i++) {
			ListEntry entry = content.get(i);
			int slotPosX = getX();
			int slotPosY = ((getY() + (i * slotHeight)) - (int) ((slotHeight * scrollBar.getProgress() * content.size())
					- (((height - slotHeight) * (scrollBar.getProgress())) / 1)));
			int slotWidth = width;
			int slotHeight = this.slotHeight;
			if ((slotPosY < (getY() + height)) && ((slotPosY + slotHeight) > getY())) {
				GlStateManager.pushMatrix();
				{
					GL11.glEnable(GL11.GL_SCISSOR_TEST);
					Minecraft mc = Minecraft.getMinecraft();
					GL11.glScissor(getX() * scale, mc.displayHeight - ((getY() + getHeight()) * scale),
							getWidth() * scale, getHeight() * scale);
					GlStateManager.resetColor();
					entry.onDraw(this, slotPosX, slotPosY, slotWidth, slotHeight, mouseX, mouseY);
					GL11.glDisable(GL11.GL_SCISSOR_TEST);
				}
				GlStateManager.popMatrix();
			}
		}
	}

	private int getScrollerSize() {
		return (int) Math.min(Math.max((int) (((1F * height) / (content.size() * slotHeight)) * (height - 4)) * 2, 15),
				height * .8);
	}

	@Override
	protected void handleMouseClickList(int mouseX, int mouseY, int mouseButtonIndex) {
		for (int i = 0; i < content.size(); i++) {
			ListEntry entry = content.get(i);
			entry.setSelected(false);
			int slotPosX = getX();
			int slotPosY = ((getY() + (i * slotHeight)) - (int) ((slotHeight * scrollBar.getProgress() * content.size())
					- (((height - slotHeight) * (scrollBar.getProgress())) / 1)));
			int slotWidth = width;
			int slotHeight = this.slotHeight;
			boolean scrollbarActive = scrollBar.isScrolling() && scrollBar.isVisible();
			if (((slotPosY + slotHeight) <= (getY() + height)) && (slotPosY >= getY()) && !scrollbarActive) {
				boolean clickedOnEntry = Geometry.isDotInArea(slotPosX, slotPosY, slotWidth, slotHeight, mouseX,
						mouseY);
				if (clickedOnEntry) {
					entry.onClick(this, mouseX, mouseY, mouseButtonIndex);
				}
			}
		}
	}

	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		return (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
				&& (mouseY <= (getY() + getHeight()));
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);

	}

	public ScrollableDisplayList setScrollAmount(float amt) {
		amt = MathHelper.clamp(amt, 0, 1.0f);
		scrollBar.setProgress(amt);
		return this;
	}

	@Override
	public void setup() {
		super.setup();
		int scrollerSize = height / (content.isEmpty() ? 1 : content.size());
		if (scrollerSize < 10) {
			scrollerSize = 10;
		}
		if (content.size() < (height / slotHeight)) {
			scrollerSize = height - 4;
		}
		scrollBar = new ScrollBar((getX() + width) - 10, getY(), 10, height, scrollerSize).setHandleMouseWheel(false);
		scrollBar.setScrollWeight(((float) height / (float) (content.size() * slotHeight)) * .8F);
		registerComponent(scrollBar);
	}

	@Override
	public GuiWidget setX(int x) {
		super.setX(x);
		if (scrollBar != null) {
			scrollBar.setX((x + width) - 10);
		}
		return this;
	}

	@Override
	public GuiWidget setY(int y) {
		super.setY(y);
		if (scrollBar != null) {
			scrollBar.setY(y);
		}
		return this;
	}

}
