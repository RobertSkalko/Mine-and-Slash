package com.libraries.rabbit.gui;

import org.apache.logging.log4j.Logger;

import com.libraries.rabbit.gui.proxy.Proxy;
import com.libraries.rabbit.gui.reference.Reference;

import net.minecraftforge.fml.common.SidedProxy;

public class RabbitGui {

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static Proxy proxy;

	public static Logger logger;

}
