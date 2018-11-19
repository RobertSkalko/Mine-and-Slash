package com.libraries.rabbit.gui.component.grid.entries;

import com.libraries.rabbit.gui.component.grid.Grid;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Entry of DisplayList component
 */
@SideOnly(Side.CLIENT)
public interface GridEntry {

	/**
	 * Called when user clicks on the list entry
	 *
	 * @param list
	 *            - DisplayList entry
	 * @param mouseX
	 *            - user's click position x
	 * @param mouseY
	 *            - user's click position y
	 */
	public default void onClick(Grid grid, int mouseX, int mouseY) {
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
	public default void onDraw(Grid grid, int posX, int posY, int width, int height, int mouseX, int mouseY) {
	}

	public default GridEntry setDoesDrawHoverText(boolean state) {
		return this;
	}
}
