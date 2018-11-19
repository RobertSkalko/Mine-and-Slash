package com.libraries.rabbit.gui.component.list.entries;

import com.libraries.rabbit.gui.component.list.DisplayList;
import com.libraries.rabbit.gui.render.Renderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class SelectListEntry<T> implements ListEntry {

	protected boolean selected = false;

	public abstract String getTitle();

	public abstract T getValue();

	public boolean isSelected() {
		return selected;
	}

	@Override
	public void onClick(DisplayList list, int mouseX, int mouseY, int mouseButtonIndex) {
		selected = !selected;
	}

	@Override
	public void onDraw(DisplayList list, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		if (isSelected()) {
			Renderer.drawRect(posX, posY, posX + width, posY + height, 0x7FA9A9FF);
		}
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
