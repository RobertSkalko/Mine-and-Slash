package com.rabbit.gui.component;

import com.rabbit.gui.base.WidgetContainer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Represents components of the screen
 *
 * @author RedEnergy
 */
@SideOnly(Side.CLIENT)
public interface IGui {

	/**
	 * Returns and id component, can be <code>null</code>
	 *
	 * @return
	 */
	String getId();

	/**
	 * Called when screen is about to be closed
	 */
	void onClose();

	/**
	 * Called on every render tick
	 *
	 * @param mouseX
	 * @param mouseY
	 * @param partialTicks
	 */
	void onDraw(int mouseX, int mouseY, float partialTicks);

	/**
	 * Called when user presses key on keyboard
	 *
	 * @param typedChar
	 * @param typedIndex
	 */
	void onKeyTyped(char typedChar, int typedIndex);

	/**
	 * Called when user clicks mouse
	 *
	 * @param posX
	 * @param posY
	 * @param mouseButtonIndex
	 * @param overlap
	 *            - indicates if there is any other component in the same location
	 *
	 * @return <code>true</code> if element has been clicked
	 */
	boolean onMouseClicked(int posX, int posY, int mouseButtonIndex, boolean overlap);

	/**
	 * Called then mouse moved or scrolled
	 */
	void onMouseInput();

	/**
	 * Called when mouse is released
	 *
	 * @param mouseX
	 * @param mouseY
	 */
	void onMouseRelease(int mouseX, int mouseY);

	/**
	 * Called every update tick (usually 20 times in second)
	 */
	void onUpdate();

	/**
	 * Sets this component id to provided
	 *
	 * @return self
	 */
	<T> IGui setId(String id);

	void setParent(WidgetContainer pane);

	/**
	 * Called on every screen resize. All components recommended to be registered in
	 * this moment
	 */
	void setup();
}
