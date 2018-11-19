package com.libraries.rabbit.gui.component.control;

import org.lwjgl.input.Keyboard;

import com.libraries.rabbit.gui.component.GuiWidget;
import com.libraries.rabbit.gui.render.TextAlignment;
import com.libraries.rabbit.gui.render.TextRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NumberPicker extends GuiWidget {

	public static interface NumberChangeListener {
		void onNumberChange(NumberPicker picker, int value);
	}

	protected int jumpValue = 10;
	protected int value = 0;
	protected int minValue = Integer.MIN_VALUE;
	protected int maxValue = Integer.MAX_VALUE;

	protected NumberChangeListener listener = (p, v) -> {
	};

	public NumberPicker() {
	}

	public NumberPicker(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public NumberPicker(int x, int y, int width, int height, int value) {
		this(x, y, width, height);
		this.value = value;
	}

	private void decrease() {
		int newValue = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? value - jumpValue : value - 1;
		if (newValue > minValue) {
			value = newValue;
		} else {
			value = minValue;
		}
		if (getListener() != null) {
			getListener().onNumberChange(this, value);
		}
	}

	public NumberChangeListener getListener() {
		return listener;
	}

	public int getValue() {
		return value;
	}

	private void increase() {
		int newValue = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? value + jumpValue : value + 1;
		if (newValue < maxValue) {
			value = newValue;
		} else {
			value = maxValue;
		}
		if (getListener() != null) {
			getListener().onNumberChange(this, value);
		}
	}

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {
		super.onDraw(mouseX, mouseY, partialTicks);
		TextRenderer.renderString(getX() + (getWidth() / 2), (getY() + (getHeight() / 2)) - 5, String.valueOf(value),
				TextAlignment.CENTER);
	}

	public NumberPicker setJumpValue(int jumpValue) {
		this.jumpValue = jumpValue;
		return this;
	}

	public NumberPicker setListener(NumberChangeListener listener) {
		this.listener = listener;
		return this;
	}

	public NumberPicker setMaxValue(int maxValue) {
		this.maxValue = maxValue;
		return this;
	}

	public NumberPicker setMinValue(int minValue) {
		this.minValue = minValue;
		return this;
	}

	@Override
	public void setup() {
		super.setup();
		registerComponent(
				new Button(getX(), getY(), getWidth(), getHeight() / 3, "+").setClickListener(btn -> increase()));
		registerComponent(new Button(getX(), getY() + ((getHeight() / 3) * 2), getWidth(), getHeight() / 3, "-")
				.setClickListener(btn -> decrease()));
	}

	public void setValue(int value) {
		this.value = value;
	}
}
