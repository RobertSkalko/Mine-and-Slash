package com.rabbit.gui.layout.argument;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ILayoutArgument {

	String fieldName();
}
