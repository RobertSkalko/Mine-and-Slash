package com.libraries.rabbit.gui.utils;

public interface DoubleChangeDispatcher {

	public void addDoubleChangeListener(DoubleChangeListener listener);

	public double getValue();

	public void setValue(double value);

}
