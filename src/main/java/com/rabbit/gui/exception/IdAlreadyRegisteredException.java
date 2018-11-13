package com.rabbit.gui.exception;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class IdAlreadyRegisteredException extends RuntimeException {

	public IdAlreadyRegisteredException() {
		super();
	}

	public IdAlreadyRegisteredException(String message) {
		super(message);
	}

}
