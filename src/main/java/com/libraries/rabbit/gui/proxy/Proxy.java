package com.libraries.rabbit.gui.proxy;

import com.libraries.rabbit.gui.base.Stage;
import com.libraries.rabbit.gui.show.IShow;

public interface Proxy {
	public void display(IShow show);

	public Stage getCurrentStage();

	public void init();

	public void postInit();

	public void preInit();

	public void renderGUI();
}