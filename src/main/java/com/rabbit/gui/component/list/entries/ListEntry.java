package com.rabbit.gui.component.list.entries;

import com.rabbit.gui.component.list.DisplayList;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Entry of DisplayList component
 */
@SideOnly(Side.CLIENT)
public interface ListEntry {

	/**
	 * @return <code> true</code> if can be clicked/selected
	 */
	public boolean isEnabled();

	/**
	 * Called when user clicks on the list entry
	 *
	 * @param list
	 *            - DisplayList entry
	 * @param mouseX
	 *            - user's click position x
	 * @param mouseY
	 *            - user's click position y
	 * @param mouseButtonIndex
	 */
	public default void onClick(DisplayList list, int mouseX, int mouseY, int mouseButtonIndex) {
	}

	/**
	 * Called when entry is drawn as a part of the list
	 *
	 * @param list
	 *            - DisplayList instance
	 * @param posX
	 *            - left position of the entry
	 * @param posY
	 *            - top position of the entry
	 * @param width
	 *            - entry width
	 * @param height
	 *            - entry height
	 * @param mouseX
	 *            - user's current mouse x
	 * @param mouseY
	 *            - user's current mouse y
	 */
	public default void onDraw(DisplayList list, int posX, int posY, int width, int height, int mouseX, int mouseY) {
	}

	public void onUpdate();

	public ListEntry setIsEnabled(boolean isEnabled);

	/**
	 * Called when user clicks on the list entry
	 *
	 * @param state
	 *            - is the entry selected
	 */
	public default void setSelected(boolean state) {
	}
}
