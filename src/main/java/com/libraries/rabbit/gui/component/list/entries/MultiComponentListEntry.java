package com.libraries.rabbit.gui.component.list.entries;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import com.google.common.collect.Lists;
import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.component.IGui;
import com.libraries.rabbit.gui.component.list.DisplayList;
import com.libraries.rabbit.gui.layout.LayoutComponent;

/**
 * Implementation of the ListEntry witch draws the given string in the center of
 * entry slot
 */
public class MultiComponentListEntry implements ListEntry {

	List<Triple<GuiWidget, Integer, Integer>> entryComponents = new ArrayList();

	@LayoutComponent
	protected boolean isEnabled = true;

	public MultiComponentListEntry() {
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

	@Override
	public void onClick(DisplayList list, int mouseX, int mouseY, int mouseButtonIndex) {
		entryComponents.forEach(com -> {
			com.getLeft().onMouseClicked(mouseX, mouseY, mouseButtonIndex, false);
		});
	}

	@Override
	public void onDraw(DisplayList list, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		entryComponents.forEach(com -> {
			com.getLeft().setX(posX + com.getMiddle());
			com.getLeft().setY(posY + com.getRight());
			com.getLeft().onDraw(mouseX, mouseY, 1);
		});
	}

	@Override
	public void onUpdate() {
		entryComponents.forEach(com -> com.getLeft().onUpdate());
	}

	/**
	 * Registers component as a part of this panel
	 *
	 * @param component
	 */
	public MultiComponentListEntry registerComponent(IGui component, int x, int y) {
		entryComponents.add(new ImmutableTriple(component, x, y));
		return this;
	}

	public ListEntry reverseComponents() {
		entryComponents = Lists.reverse(entryComponents);
		return this;
	}

	@Override
	public ListEntry setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}

}