package com.rabbit.gui.utils;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses the EventQueue to process its events, but you should only
 * really do this if the changes you make have an impact on part of a GUI eg.
 * adding a button to a JFrame.
 *
 * Otherwise, you should create your own event dispatch thread that can handle
 * change events
 */
public class DoubleListener implements DoubleChangeDispatcher {

	private double value;
	private List<DoubleChangeListener> listeners;

	public DoubleListener(double initialState) {
		value = initialState;
		listeners = new ArrayList<>();
	}

	@Override
	public void addDoubleChangeListener(DoubleChangeListener listener) {
		listeners.add(listener);
	}

	private void dispatchEvent() {
		final DoubleChangeEvent event = new DoubleChangeEvent(this);
		for (DoubleChangeListener l : listeners) {
			dispatchRunnableOnEventQueue(l, event);
		}
	}

	private void dispatchRunnableOnEventQueue(final DoubleChangeListener listener, final DoubleChangeEvent event) {
		EventQueue.invokeLater(() -> listener.stateChanged(event));
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public void setValue(double value) {
		if (this.value != value) {
			this.value = value;
			dispatchEvent();
		}
	}
}
