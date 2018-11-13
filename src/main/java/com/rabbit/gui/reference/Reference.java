package com.rabbit.gui.reference;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Reference {

	public static final String MOD_ID = "rabbit-gui";
	public static final String MOD_NAME = "Rabbit Gui Library";

	public static final String SERVER_PROXY_CLASS = "com.rabbit.gui.proxy.Server";
	public static final String CLIENT_PROXY_CLASS = "com.rabbit.gui.proxy.Client";

}