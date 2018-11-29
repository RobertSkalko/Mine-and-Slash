package com.robertx22.mmorpg;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Keybinds {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stats", Keyboard.KEY_P, "Mine and Slash");

    public static KeyBinding Map_Stats = new KeyBinding("Current Map Stats", Keyboard.KEY_F7, "Mine and Slash");

    public static KeyBinding Map_Drops = new KeyBinding("What Unique Items Current Map can drop", Keyboard.KEY_F6,
	    "Mine and Slash");

    public static void register() {
	ClientRegistry.registerKeyBinding(Player_Stats);
	ClientRegistry.registerKeyBinding(Map_Stats);
	ClientRegistry.registerKeyBinding(Map_Drops);
    }
}
