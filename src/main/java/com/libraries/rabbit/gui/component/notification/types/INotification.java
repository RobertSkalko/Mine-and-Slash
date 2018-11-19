package com.libraries.rabbit.gui.component.notification.types;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public interface INotification {
	public static final ResourceLocation guiTexture = new ResourceLocation(
			"textures/gui/achievement/achievement_background.png");

	public void drawNotification(Minecraft mc);

	public void updateWindowScale(Minecraft mc);
}
