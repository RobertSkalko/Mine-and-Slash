package com.rabbit.gui.utils;

import java.util.EventListener;

/**
 * Listener interface for classes interested in knowing about a boolean flag
 * change.
 */
public interface DoubleChangeListener extends EventListener {

	public void stateChanged(DoubleChangeEvent event);

}