package com.libraries.rabbit.gui.proxy;

import com.libraries.rabbit.gui.base.Stage;
import com.libraries.rabbit.gui.component.display.entity.DisplayEntity;
import com.libraries.rabbit.gui.component.display.entity.DisplayEntityHead;
import com.libraries.rabbit.gui.component.display.entity.DisplayEntityHeadRenderer;
import com.libraries.rabbit.gui.component.display.entity.DisplayEntityRenderer;
import com.libraries.rabbit.gui.component.hud.overlay.Overlay;
import com.libraries.rabbit.gui.component.notification.NotificationsManager;
import com.libraries.rabbit.gui.show.IShow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Client implements Proxy {

	/**
	 * If there are any currently opened Stage it will display given show in it <br>
	 * Otherwise will create new Stage
	 *
	 * @param show
	 */
	@Override
	public void display(IShow show) {
		Stage current = getCurrentStage();
		if (current != null) {
			current.setShow(show);
			current.reinitShow();
		} else {
			Minecraft.getMinecraft().displayGuiScreen(new Stage(show));
		}
	}

	/**
	 * Returns currently opened Stage, may be null
	 */
	@Override
	public Stage getCurrentStage() {
		return Minecraft.getMinecraft().currentScreen instanceof Stage ? (Stage) Minecraft.getMinecraft().currentScreen
				: null;
	}

	@Override
	public void init() {
	}

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event) {
		if (Minecraft.getMinecraft().inGameHasFocus) {
			Overlay.draw();
		}
		if (!(Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu)) {
			NotificationsManager.renderNotifications();
		}
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void preInit() {
		RenderingRegistry.registerEntityRenderingHandler(DisplayEntity.class, new DisplayEntityRenderer());
		RenderingRegistry.registerEntityRenderingHandler(DisplayEntityHead.class, new DisplayEntityHeadRenderer());
	}

	/**
	 * @see forge.reference.proxy.Proxy#renderGUI()
	 */
	@Override
	public void renderGUI() {
		// Render GUI when on call from client
	}
}