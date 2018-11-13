package com.rabbit.gui.component.notification;

import java.util.ArrayList;
import java.util.List;

import com.rabbit.gui.component.notification.types.INotification;

import net.minecraft.client.Minecraft;

public class NotificationsManager {
	private static final List<INotification> notificationList = new ArrayList<>();
	private static final Minecraft mc = Minecraft.getMinecraft();

	public static void addNotification(INotification notification) {
		NotificationsManager.notificationList.add(notification);
	}

	public static void removeNotification(INotification notification) {
		NotificationsManager.notificationList.remove(notification);
	}

	public static void renderNotifications() {
		for (INotification notification : NotificationsManager.notificationList) {
			notification.drawNotification(NotificationsManager.mc);
		}
	}
}
