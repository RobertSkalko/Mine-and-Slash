package com.rabbit.gui.component.control;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.rabbit.gui.RabbitGui;
import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.component.Shiftable;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.render.TextAlignment;
import com.rabbit.gui.render.TextRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Simple button component <br>
 * Supported width: <b> 0 - 400 </b> (due to texture length it can't be larger)
 * <br>
 * Supported height: <b> 5 - INFINITY </b> <br>
 *
 * Use {@link #setClickListener(ButtonClickListener)} to define action on button
 * pressed
 */

@SideOnly(Side.CLIENT)
@LayoutComponent
public class Button extends GuiWidget implements Shiftable {

	@FunctionalInterface
	public static interface ButtonClickListener {
		void onClick(Button button);
	}

	protected final static int DISABLED_STATE = 0;
	protected final static int IDLE_STATE = 1;
	protected final static int HOVER_STATE = 2;

	protected boolean drawHoverText = false;
	protected List<String> originalHoverText = new ArrayList<>();
	protected List<String> hoverText = new ArrayList<>();

	protected ResourceLocation buttonTexture = new ResourceLocation("textures/gui/widgets.png");

	@LayoutComponent
	protected String text;

	@LayoutComponent
	protected boolean isVisible = true;

	@LayoutComponent
	protected boolean isEnabled = true;

	protected ButtonClickListener onClick;

	protected boolean drawToLeft;

	/** Dummy constructor. Used in layout */
	protected Button() {
	}

	public Button(int xPos, int yPos, int width, int height, String title) {
		super(xPos, yPos, width, height);
		text = title;
	}

	public Button addHoverText(String text) {
		originalHoverText.add(text);
		return this;
	}

	protected void drawButton(int state) {
		Renderer.drawContinuousTexturedBox(getX(), getY(), 0, 46 + (20 * state), getWidth(), getHeight(), 200, 20, 2, 3,
				2, 2);
	}

	protected void endRender() {
		GlStateManager.resetColor();
		GlStateManager.disableBlend();
	}

	public ResourceLocation getButtonTexture() {
		return buttonTexture;
	}

	public ButtonClickListener getClickListener() {
		return onClick;
	}

	public List<String> getHoverText() {
		return originalHoverText;
	}

	public String getText() {
		return text;
	}

	public boolean isButtonUnderMouse(int mouseX, int mouseY) {
		return (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
				&& (mouseY <= (getY() + getHeight()));
	}

	/**
	 * @return <code> true</code> if button can be clicked
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * @return <code> true </code> if button would be rendered
	 */
	public boolean isVisible() {
		return isVisible;
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		if (isVisible()) {
			GlStateManager.pushMatrix();
			{
				prepareRender();
				if (!isEnabled()) {
					drawButton(Button.DISABLED_STATE);
				} else if (isButtonUnderMouse(mouseX, mouseY)) {
					drawButton(Button.HOVER_STATE);
					if (drawHoverText) {
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
				} else {
					drawButton(Button.IDLE_STATE);
				}
				TextRenderer.renderString(getX() + (getWidth() / 2), (getY() + (getHeight() / 2)) - 4, getText(),
						TextAlignment.CENTER);
			}
			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isButtonUnderMouse(posX, posY) && isEnabled() && !overlap;
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

	protected void prepareRender() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(getButtonTexture());
		GlStateManager.resetColor();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Provided listener will be executed by pressing the button
	 *
	 * @param onClicked
	 *            listener
	 * @return self
	 */
	public Button setClickListener(ButtonClickListener onClicked) {
		onClick = onClicked;
		return this;
	}

	public Button setCustomTexture(ResourceLocation res) {
		buttonTexture = res;
		return this;
	}

	public Button setDoesDrawHoverText(boolean state) {
		drawHoverText = state;
		return this;
	}

	public Button setHoverText(List<String> text) {
		originalHoverText = text;
		return this;
	}

	@Override
	public Button setId(String id) {
		assignId(id);
		return this;
	}

	public Button setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

	public Button setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public Button setText(String text) {
		this.text = text;
		return this;
	}

	@Override
	public void shiftX(int x) {
		setX(getX() + x);
	}

	@Override
	public void shiftY(int y) {
		setY(getY() + y);
	}

	protected void verifyHoverText(int mouseX, int mouseY) {
		int tlineWidth = 0;
		for (String line : originalHoverText) {
			tlineWidth = TextRenderer.getFontRenderer().getStringWidth(line) > tlineWidth
					? TextRenderer.getFontRenderer().getStringWidth(line)
					: tlineWidth;
		}
		int dWidth = RabbitGui.proxy.getCurrentStage().width;
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
