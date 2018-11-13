package com.rabbit.gui.base;

import java.util.List;

import com.rabbit.gui.component.IGui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface WidgetContainer {

	@SuppressWarnings("unchecked")
	default <T> T findComponentById(String id) {
		return (T) getComponentsList().stream().filter(com -> id.equals(com.getId())).findFirst().orElse(null);
	}

	List<IGui> getComponentsList();

	/**
	 * Returns pane in which this component registered
	 *
	 * @return parent pane - can be null if it hasn't been registered yet
	 */
	WidgetContainer getParent();

	/**
	 * Called when component registered in pane
	 */
	void onRegistered(WidgetContainer pane);

	/**
	 * Registers component as a part of pane
	 *
	 * @param component
	 */
	void registerComponent(IGui component);

}
