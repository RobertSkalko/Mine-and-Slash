package com.rabbit.gui.component.list.entries;

import java.awt.Color;

import com.rabbit.gui.component.list.DisplayList;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.TextAlignment;
import com.rabbit.gui.render.TextRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SelectElementEntry<T> extends SelectListEntry {

	public static interface OnClickListener {
		void onClick(SelectElementEntry entry, DisplayList list, int mouseX, int mouseY);
	}

	@LayoutComponent
	protected boolean isEnabled = true;

	/**
	 * String which would be drawn in the center of the entry <br>
	 * If it doesn't fits into slot width it would be trimmed
	 */
	private final String title;
	private final T entryValue;

	@LayoutComponent
	protected TextAlignment align = TextAlignment.CENTER;

	@LayoutComponent
	private Color color;

	/**
	 * Listener which would be called when user click the entry
	 */
	private OnClickListener listener;

	public SelectElementEntry(T value, String title) {
		this(value, title, Color.WHITE, null);
	}

	public SelectElementEntry(T value, String title, Color color) {
		this(value, title, color, null);
	}

	public SelectElementEntry(T value, String title, Color color, OnClickListener listener) {
		this.entryValue = value;
		this.title = title;
		this.listener = listener;
		this.color = color;
	}

	public SelectElementEntry(T value, String title, OnClickListener listener) {
		this(value, title, Color.WHITE, listener);
	}

	public TextAlignment getAlignment() {
		return align;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public T getValue() {
		return this.entryValue;
	}

	/**
	 * @return <code> true</code> if button can be clicked
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	@Override
	public void onClick(DisplayList list, int mouseX, int mouseY, int mouseButtonIndex) {
		if (isEnabled) {
			super.onClick(list, mouseX, mouseY, mouseButtonIndex);
			if (listener != null) {
				listener.onClick(this, list, mouseX, mouseY);
			}
		} else {
			setSelected(false);
		}

	}

	@Override
	public void onDraw(DisplayList list, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		super.onDraw(list, posX, posY, width, height, mouseX, mouseY);
		if (isEnabled()) {
			if (align == TextAlignment.CENTER) {
				TextRenderer.renderString(posX + (width / 2), (posY + (height / 2)) - 5,
						TextRenderer.getFontRenderer().trimStringToWidth(title, width), align);
			} else if (align == TextAlignment.LEFT) {
				TextRenderer.renderString(posX + 2, (posY + (height / 2)) - 5,
						TextRenderer.getFontRenderer().trimStringToWidth(title, width), align);
			} else if (align == TextAlignment.RIGHT) {
				TextRenderer.renderString((posX + width) - 2, (posY + (height / 2)) - 5,
						TextRenderer.getFontRenderer().trimStringToWidth(title, width), align);
			}
		} else {
			if (align == TextAlignment.CENTER) {
				TextRenderer.renderString(posX + (width / 2), (posY + (height / 2)) - 5,
						TextRenderer.getFontRenderer().trimStringToWidth(title, width), Color.gray, align);
			} else if (align == TextAlignment.LEFT) {
				TextRenderer.renderString(posX + 2, (posY + (height / 2)) - 5,
						TextRenderer.getFontRenderer().trimStringToWidth(title, width), Color.gray, align);
			} else if (align == TextAlignment.RIGHT) {
				TextRenderer.renderString((posX + width) - 2, (posY + (height / 2)) - 5,
						TextRenderer.getFontRenderer().trimStringToWidth(title, width), Color.gray, align);
			}
		}
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub

	}

	public SelectElementEntry<T> setAlignment(TextAlignment align) {
		this.align = align;
		return this;
	}

	@Override
	public SelectElementEntry<T> setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		return this;
	}
}
