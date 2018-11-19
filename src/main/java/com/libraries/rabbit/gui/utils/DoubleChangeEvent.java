package com.libraries.rabbit.gui.utils;

import java.util.EventObject;

/**
 * This class lets the listener know when the change occured and what object was
 * changed.
 */
public class DoubleChangeEvent extends EventObject {

	private final DoubleChangeDispatcher dispatcher;

	public DoubleChangeEvent(DoubleChangeDispatcher dispatcher) {
		super(dispatcher);
		this.dispatcher = dispatcher;
	}

	// type safe way to get source (as opposed to getSource of EventObject
	public DoubleChangeDispatcher getDispatcher() {
		return dispatcher;
	}
}
