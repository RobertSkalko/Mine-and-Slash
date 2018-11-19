package com.libraries.rabbit.gui.component.control;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.component.Shiftable;
import com.libraries.rabbit.gui.layout.LayoutComponent;
import com.libraries.rabbit.gui.render.Renderer;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@LayoutComponent
public class CheckBox extends GuiWidget implements Shiftable {

	// width and height of checkbox are hardcoded and can't be changed
	// if you need to change it use glScalef

	@FunctionalInterface
	public interface CheckBoxStatusChangedListener {
		void onStatusChanged(CheckBox box);
	}

	// hard coded sizes? why
	protected static final int WIDTH = 11;

	protected static final int HEIGHT = 11;

	@LayoutComponent
	protected Color checkColor;
	@LayoutComponent
	protected Color textColor;

	protected ResourceLocation buttonTexture = new ResourceLocation("textures/gui/widgets.png");

	@LayoutComponent
	protected boolean isChecked;

	@LayoutComponent
	protected String text;

	@LayoutComponent
	protected boolean isVisible = true;

	@LayoutComponent
	protected boolean isEnabled = true;

	protected CheckBoxStatusChangedListener onStatusChangedListener;

	public CheckBox(int xPos, int yPos, int width, int height, Color checkColor, Color textColor, String title,
			boolean checked) {
		super(xPos, yPos, width, height);
		this.textColor = textColor;
		this.checkColor = checkColor;
		text = title;
		isChecked = checked;
	}

	public CheckBox(int xPos, int yPos, int width, int height, String title, boolean checked) {
		this(xPos, yPos, width, height, Color.GREEN, Color.white, title, checked);
	}

	public CheckBox(int xPos, int yPos, String title, boolean checked) {
		this(xPos, yPos, CheckBox.WIDTH, CheckBox.HEIGHT, Color.GREEN, Color.white, title, checked);
	}

	public CheckBox(int xPos, int yPos, String title, Color textColor, boolean checked) {
		this(xPos, yPos, CheckBox.WIDTH, CheckBox.HEIGHT, Color.GREEN, textColor, title, checked);
	}

	public CheckBox(int xPos, int yPos, String title, Color checkColor, Color textColor, boolean checked) {
		this(xPos, yPos, CheckBox.WIDTH, CheckBox.HEIGHT, checkColor, textColor, title, checked);
	}

	void b(ResourceLocation loc) {
		Minecraft.getMinecraft().getTextureManager().getTexture(loc);
	}

	protected void drawButton() {
		Renderer.drawContinuousTexturedBox(getX(), getY(), 0, 46, getWidth(), getHeight(), 200, 20, 2, 3, 2, 2);
	}

	public ResourceLocation getButtonTexture() {
		return buttonTexture;
	}

	public CheckBoxStatusChangedListener getStatusChangedListener() {
		return onStatusChangedListener;
	}

	public String getText() {
		return text;
	}

	public boolean isButtonUnderMouse(int mouseX, int mouseY) {
		return (mouseX >= getX()) && (mouseX <= (getX() + getWidth())) && (mouseY >= getY())
				&& (mouseY <= (getY() + getHeight()));
	}

	public boolean isChecked() {
		return isChecked;
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
			prepareRender();
			drawButton();
			if (isChecked()) {
				Renderer.drawLine(getX() + 2, (int) (getY() + (getHeight() * .66)), getX() + (getWidth() / 2),
						(getY() + getHeight()) - 2, checkColor, getWidth() / 3);
				Renderer.drawLine(getX() + (getWidth() / 2), (getY() + getHeight()) - 2, getX() + getWidth() + 1,
						getY() - 1, checkColor, getWidth() / 3);
			}
			TextRenderer.renderString(getX() + getWidth() + 2, (getY() + (getHeight() / 2)) - 3, getText(), textColor);
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		boolean clicked = isButtonUnderMouse(posX, posY) && isEnabled() && !overlap;
		if (clicked) {
			setIsCheckedWithNotify(!isChecked());
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
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
		// OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
	}

	public CheckBox setCustomTexture(ResourceLocation res) {
		buttonTexture = res;
		return this;
	}

	@Override
	public CheckBox setId(String id) {
		assignId(id);
		return this;
	}

	public CheckBox setIsChecked(boolean state) {
		isChecked = state;
		return this;
	}

	public CheckBox setIsCheckedWithNotify(boolean state) {
		setIsChecked(state);
		if (getStatusChangedListener() != null) {
			getStatusChangedListener().onStatusChanged(this);
		}
		return this;
	}

	public CheckBox setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

	public CheckBox setIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public CheckBox setStatusChangedListener(CheckBoxStatusChangedListener listener) {
		onStatusChangedListener = listener;
		return this;
	}

	public CheckBox setText(String text) {
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

}
