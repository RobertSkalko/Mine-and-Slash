package com.rabbit.gui.component.display.tabs;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.RabbitGui;
import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public abstract class Tab extends GuiWidget {

	@FunctionalInterface
	public static interface TabClickListener {
		void onClick(Tab tab);
	}

	protected final ResourceLocation TABS = new ResourceLocation("rabbit", "textures/gui/sm_notification2.png");
	private boolean isVisible;
	protected boolean isHidden = false;
	protected TabClickListener onClick;

	protected List<String> originalHoverText = new ArrayList<>();
	protected List<String> hoverText = new ArrayList<>();

	protected int angle;

	protected boolean drawHoverText = true;

	protected boolean drawToLeft;

	public Tab(int x, int y, int width, int height, int angle) {
		super(x, y, width, height);
		this.angle = angle;
	}

	protected void beginDrawingTab(int mouseX, int mouseY, float partialTicks) {
		if (angle != 0) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

			GlStateManager.translate(x, y, -1);
			GlStateManager.color(1, 1, 1, 1);
			GlStateManager.rotate(angle, 0.0F, 0.0F, 1.0F);
			if (isHidden) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TABS);
				drawScaledTexturedRect(0, -height + 4, 1, width, height);
				GlStateManager.rotate(-angle, 0.0F, 0.0F, 1.0F);
			} else {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TABS);
				drawScaledTexturedRect(0, 0, 1, width, height);
				GlStateManager.rotate(-angle, 0.0F, 0.0F, 1.0F);
			}
		} else {
			if (isHidden) {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TABS);
				drawScaledTexturedRect(x, y <= 0 ? (y - height) + 4 : (y + height) - 4, 1, width, height);
			} else {
				Minecraft.getMinecraft().getTextureManager().bindTexture(TABS);
				drawScaledTexturedRect(x, y, 1, width, height);
			}
		}
	}

	public boolean doesDrawHoverText() {
		return drawHoverText;
	}

	public void drawHoverText(int mouseX, int mouseY, float partialTicks) {
		if (drawHoverText && isUnderMouse(mouseX, mouseY)) {
			GlStateManager.pushMatrix();
			{
				verifyHoverText(mouseX, mouseY);
				if (drawToLeft) {
					int tlineWidth = 0;
					for (String line : hoverText) {
						tlineWidth = TextRenderer.getFontRenderer().getStringWidth(line) > tlineWidth
								? TextRenderer.getFontRenderer().getStringWidth(line)
								: tlineWidth;
					}
					Renderer.drawHoveringText(hoverText, mouseX - tlineWidth - 20, mouseY + 12);
				} else {
					Renderer.drawHoveringText(hoverText, mouseX, mouseY + 12);
				}
			}
			GlStateManager.popMatrix();
		}
	}

	protected void drawScaledTexturedRect(int x, int y, int z, int width, int height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x + width, y + height, z).tex(1, 1).endVertex();
		bufferbuilder.pos(x + width, y, z).tex(1, 0).endVertex();
		bufferbuilder.pos(x, y, z).tex(0, 0).endVertex();
		bufferbuilder.pos(x, y + height, z).tex(0, 1).endVertex();
		tessellator.draw();
	}

	protected void finishDrawingTab(int mouseX, int mouseY, float partialTicks) {
		if (angle != 0) {
			GlStateManager.popMatrix();
			GlStateManager.resetColor();
		}
	}

	public int getAngle() {
		return angle;
	}

	public TabClickListener getClickListener() {
		return onClick;
	}

	public List<String> getHoverText() {
		return originalHoverText;
	}

	public boolean isHidden() {
		return isHidden;
	}

	@Override
	public boolean isUnderMouse(int mouseX, int mouseY) {
		if (angle == 180) {
			return isUnderMouseHorizontal(mouseX + width, mouseY);
		} else if (angle == 90) {
			return isUnderMouseVertical(mouseX + height, mouseY);
		} else if (angle == 270) {
			return isUnderMouseVertical(mouseX - height, mouseY);
		} else {
			return isUnderMouseHorizontal(mouseX, mouseY);
		}
	}

	private boolean isUnderMouseHorizontal(int mouseX, int mouseY) {
		boolean retVal = false;
		if (!isHidden) {
			retVal = (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
					&& (mouseY <= (getY() + getHeight()));
		} else {
			if (angle == 90) {
				retVal = (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
						&& (mouseY <= (getY() + 4));
			} else {
				retVal = (mouseX >= getX()) && (mouseX <= (getX() + getWidth()))
						&& (mouseY >= ((getY() + getHeight()) - 4)) && (mouseY <= (getY() + getHeight()));
			}
		}
		return retVal;
	}

	private boolean isUnderMouseVertical(int mouseX, int mouseY) {
		boolean retVal = false;
		if (!isHidden) {
			retVal = (mouseX >= getX()) && (mouseX <= (getX() + getHeight())) && (mouseY >= getY())
					&& (mouseY <= (getY() + getWidth()));
		} else {
			if (angle == 90) {
				retVal = (mouseX >= ((getX() + getHeight()) - 4)) && (mouseX <= (getX() + getHeight()))
						&& (mouseY >= getY()) && (mouseY <= (getY() + getWidth()));
			} else {
				retVal = (mouseX >= getX()) && (mouseX <= (getX() + 4)) && (mouseY >= getY())
						&& (mouseY <= (getY() + getWidth()));
			}
		}
		return retVal;
	}

	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isUnderMouse(posX, posY) && !overlap;
		if (clicked) {
			if (getClickListener() != null) {
				getClickListener().onClick(this);
			}
			playClickSound();
		}
		return clicked;
	}

	protected void playClickSound() {
		Minecraft.getMinecraft().getSoundHandler()
				.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Tab setClickListener(TabClickListener onClicked) {
		onClick = onClicked;
		return this;
	}

	public Tab setDrawHoverText(boolean drawHoverText) {
		this.drawHoverText = drawHoverText;
		return this;
	}

	public Tab setHidden(boolean isHidden) {
		this.isHidden = isHidden;
		return this;
	}

	public Tab setHoverText(List<String> text) {
		originalHoverText = text;
		return this;
	}

	@Override
	public Tab setId(String id) {
		assignId(id);
		return this;
	}

	public Tab setIsVisible(boolean visible) {
		isVisible = visible;
		return this;
	}

	protected void verifyHoverText(int mouseX, int mouseY) {
		int tlineWidth = 0;
		for (String line : originalHoverText) {
			tlineWidth = TextRenderer.getFontRenderer().getStringWidth(line) > tlineWidth
					? TextRenderer.getFontRenderer().getStringWidth(line)
					: tlineWidth;
		}
		int dWidth = RabbitGui.proxy.getCurrentStage() == null ? Minecraft.getMinecraft().displayWidth
				: RabbitGui.proxy.getCurrentStage().width;
		if (((tlineWidth + mouseX) > dWidth) && ((mouseX + 1) > (dWidth / 2))) {
			// the button is on the right half of the screen
			drawToLeft = true;
		}
		List<String> newHoverText = new ArrayList<>();
		if (drawToLeft) {
			for (String line : originalHoverText) {
				int lineWidth = TextRenderer.getFontRenderer().getStringWidth(line) + 12;
				// if the line length is longer than the button is from the left
				// side of the screen we have to split
				if (lineWidth > mouseX) {
					// the line is too long lets split it
					String newString = "";
					for (String substring : line.split(" ")) {
						// we can fit the string, we are ok
						if ((TextRenderer.getFontRenderer().getStringWidth(newString)
								+ TextRenderer.getFontRenderer().getStringWidth(substring)) < (mouseX - 12)) {
							newString += substring + " ";
						} else {
							newHoverText.add(newString);
							newString = substring + " ";
						}
					}
					newHoverText.add(newString);
				} else {
					newHoverText.add(line);
				}
			}
		} else {
			for (String line : originalHoverText) {
				int lineWidth = TextRenderer.getFontRenderer().getStringWidth(line) + 12;
				// we just need to know what the right most side of the button
				// is
				if (lineWidth > (dWidth - mouseX)) {
					// the line is too long lets split it
					String newString = "";
					for (String substring : line.split(" ")) {
						// we can fit the string, we are ok
						if ((TextRenderer.getFontRenderer().getStringWidth(newString)
								+ TextRenderer.getFontRenderer().getStringWidth(substring)) < (dWidth - mouseX - 12)) {
							newString += substring + " ";
						} else {
							newHoverText.add(newString);
							newString = substring + " ";
						}
					}
					newHoverText.add(newString);
				} else {
					newHoverText.add(line);
				}
			}
		}
		hoverText = newHoverText;
	}
}
