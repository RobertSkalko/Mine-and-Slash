package com.libraries.rabbit.gui.component.display;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL20;

import com.google.common.collect.Lists;
import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.component.IGui;
import com.libraries.rabbit.gui.component.control.Button;
import com.libraries.rabbit.gui.component.display.tabs.Tab;
import com.libraries.rabbit.gui.render.ShaderProgram;
import com.libraries.rabbit.gui.utils.Geometry;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Panel extends GuiWidget {

	private static final ShaderProgram SHADER_ALPHA = new ShaderProgram("rabbit", null, "shaders/alpha.frag");

	List<GuiWidget> panelComponents = new ArrayList<>();
	private boolean isDragging;
	private boolean isResizing;
	private boolean canDrag;
	private boolean canResize;
	private int dragXDelta, dragYDelta;
	private int resizeXPos;
	private int resizeYPos;
	private boolean isVisible;
	private boolean isFocused;
	private boolean doesDim;

	private int z = 0;

	public Panel(int xPos, int yPos, int width, int height) {
		this(xPos, yPos, width, height, true);
	}

	public Panel(int xPos, int yPos, int width, int height, boolean visible) {
		super(xPos, yPos, width, height);
		isDragging = false;
		isResizing = false;
		canDrag = false;
		canResize = false;
		isVisible = visible;
		isFocused = false;
		doesDim = false;
	}

	public boolean canDrag() {
		return canDrag;
	}

	public boolean canResize() {
		return canResize;
	}

	public boolean doesDim() {
		return doesDim;
	}

	public int getZ() {
		return z;
	}

	public boolean isFocused() {
		return isFocused;
	}

	public void movePanel(int newX, int newY) {
		float xDelta = x - newX;
		float yDelta = y - newY;
		if ((xDelta == 0) && (yDelta == 0)) {
			return;
		}
		panelComponents.forEach(com -> {
			com.setX((int) (com.getX() - xDelta));
			com.setY((int) (com.getY() - yDelta));
		});
		x = newX;
		y = newY;
	}

	@Override
	public void onDraw(int xMouse, int yMouse, float partialTicks) {
		if (isVisible) {
			if (isFocused) {
				if (isDragging) {
					movePanel(xMouse - dragXDelta, yMouse - dragYDelta);
				}
				if (isResizing) {
					// movePanel(xMouse - dragXDelta, y);
					resize(width - (resizeXPos - xMouse), height - (resizeYPos - yMouse));
					resizeXPos = xMouse;
					resizeYPos = yMouse;
					// movePanel(xMouse - dragXDelta, yMouse - dragYDelta);
					// setSize(width + (resizeXPos-xMouse), height + (resizeYPos
					// -
					// yMouse));
				}
			}
			GlStateManager.translate(0, 0, z);

			boolean shouldDim = false;

			if (doesDim) {
				shouldDim = !Geometry.isDotInArea(x, y, width, height, xMouse, yMouse);
				for (GuiWidget com : panelComponents) {
					if (com.isUnderMouse(xMouse, yMouse)) {
						shouldDim = false;
					}
				}
			}
			if (shouldDim) {
				if ((OpenGlHelper.shadersSupported)) {

					GL20.glUseProgram(Panel.SHADER_ALPHA.getProgram());
					GL20.glUniform1f(GL20.glGetUniformLocation(Panel.SHADER_ALPHA.getProgram(), "alpha_multiplier"),
							0.6f);
				}
			}
			panelComponents.forEach(com -> com.onDraw(xMouse, yMouse, partialTicks));
			if (shouldDim) {
				GL20.glUseProgram(0);
			}
		}
	}

	@Override
	public void onKeyTyped(char typedChar, int typedIndex) {
		if (isVisible && isFocused) {
			panelComponents.forEach(com -> com.onKeyTyped(typedChar, typedIndex));
		}
	}

	@Override
	public boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap) {
		if (isUnderMouse(posX, posY)) {
			isFocused = true;
		} else {
			isFocused = false;
		}
		if (isVisible) {
			if (isFocused) {
				super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);

				if (canDrag) {
					isDragging = !overlap && Geometry.isDotInArea(x, y + 10, 5, height - 10, posX, posY);
				}
				if (canResize) {
					isResizing = !overlap && Geometry.isDotInArea((int) ((x + width) - (width * .1)), height - 10,
							(int) (width * .1), 10, posX, posY);
				}
				if (isDragging) {
					dragXDelta = posX - x;
					dragYDelta = posY - y;
				}
				if (isResizing) {
					resizeXPos = posX;
					resizeYPos = posY;
				}

				panelComponents.forEach(com -> {
					if (com.onMouseClicked(posX, posY, mouseButtonIndex, overlap)) {
						isDragging = isResizing = false;
					}
				});

				return true;
			} else {
				// since tabs arent within the panel but we still need them to be clickable
				panelComponents.forEach(com -> {
					if (com instanceof Tab) {
						com.onMouseClicked(posX, posY, mouseButtonIndex, overlap);
					}
				});
			}
		}

		return super.onMouseClicked(posX, posY, mouseButtonIndex, overlap);
	}

	@Override
	public void onMouseInput() {
		if (isVisible && isFocused) {
			panelComponents.forEach(com -> com.onMouseInput());
		}
	}

	@Override
	public void onMouseRelease(int mouseX, int mouseY) {
		super.onMouseRelease(mouseX, mouseY);
		if (isVisible && isFocused) {
			panelComponents.forEach(com -> com.onMouseRelease(mouseX, mouseY));
			if (isDragging) {
				isDragging = false;
			}
			if (isResizing) {
				isResizing = false;
			}
		}
	}

	@Override
	public void onUpdate() {
		if (isVisible && isFocused) {
			panelComponents.forEach(com -> com.onUpdate());
		}
	}

	/**
	 * Registers component as a part of this panel
	 *
	 * @param component
	 */
	@Override
	public void registerComponent(IGui component) {
		GuiWidget widget = (GuiWidget) component;
		// set the component in relative location to panel
		widget.setX(widget.getX() + x);
		widget.setY(widget.getY() + y);
		panelComponents.add(widget);
	}

	public void resize(int newWidth, int newHeight) {
		float widthDelta = newWidth / ((float) width);
		float heightDelta = newHeight / ((float) height);
		panelComponents.forEach(com -> {
			if (!(com instanceof Button)) {
				com.setWidth((int) (com.getWidth() * widthDelta));
				com.setHeight((int) (com.getHeight() * heightDelta));
			}
		});
		setSize(newWidth, newHeight);
	}

	public Panel reverseComponents() {
		panelComponents = Lists.reverse(panelComponents);
		return this;
	}

	public Panel setCanDrag(boolean canDrag) {
		this.canDrag = canDrag;
		return this;
	}

	public void setDimming(boolean doesDim) {
		this.doesDim = doesDim;
	}

	public Panel setFocused(boolean isFocused) {
		this.isFocused = isFocused;
		return this;
	}

	public Panel setSize(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public void setup() {
		panelComponents.forEach(com -> com.setup());
	}

	// resizing doesnt work appropriately yet
	// public Panel setCanResize(boolean canResize) {
	// this.canResize = canResize;
	// return this;
	// }

	public Panel setVisible(boolean state) {
		if (!state) {
			// cant be invisible and focused
			isFocused = false;
		}
		isVisible = state;
		return this;
	}

	public Panel setZ(int zPos) {
		z = zPos;
		return this;
	}
}
