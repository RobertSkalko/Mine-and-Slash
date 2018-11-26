package com.robertx22.mmorpg;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds {

	public static KeyBinding Player_Stats;
	public static KeyBinding Map_Stats;

	public static void register()

	{
		Player_Stats = new KeyBinding("Opens a Gui that shows your rpg stats", Keyboard.KEY_P, "Mine and Slash");
		Map_Stats = new KeyBinding("Opens a Gui that shows current map stats", Keyboard.KEY_L, "Mine and Slash");

		ClientRegistry.registerKeyBinding(Player_Stats);
		ClientRegistry.registerKeyBinding(Map_Stats);
	}
}
