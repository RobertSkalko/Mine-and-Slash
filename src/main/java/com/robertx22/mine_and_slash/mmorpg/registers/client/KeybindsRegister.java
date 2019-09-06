package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeybindsRegister {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stats", GLFW.GLFW_KEY_P, Ref.MOD_NAME);

    public static void register() {
        ClientRegistry.registerKeyBinding(Player_Stats);
    }

}
