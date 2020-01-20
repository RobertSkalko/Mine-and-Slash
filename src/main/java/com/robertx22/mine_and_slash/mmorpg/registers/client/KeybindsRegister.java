package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class KeybindsRegister {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stat Overview", GLFW.GLFW_KEY_P, Ref.MOD_NAME);
    public static KeyBinding Player_Stat_Points = new KeyBinding("Player Stat Points", GLFW.GLFW_KEY_O, Ref.MOD_NAME);
    public static KeyBinding Talent_Tree = new KeyBinding("Talent Tree", GLFW.GLFW_KEY_J, Ref.MOD_NAME);
    public static KeyBinding disableNeatOverlay = new KeyBinding("Disable Neat Overlay", GLFW.GLFW_KEY_MINUS, Ref.MOD_NAME);
    public static KeyBinding mapInfo = new KeyBinding("Adventure Map Info", GLFW.GLFW_KEY_M, Ref.MOD_NAME);
    public static KeyBinding hubScreen = new KeyBinding("Hub Screen", GLFW.GLFW_KEY_H, Ref.MOD_NAME);

    public static HashMap<KeyBinding, Integer> HOTBAR = new HashMap<KeyBinding, Integer>() {{
        put(new KeyBinding("Spell Hotbar key 1", GLFW.GLFW_KEY_LEFT_SHIFT + GLFW.GLFW_KEY_1, Ref.MOD_NAME), 0);
        put(new KeyBinding("Spell Hotbar key 2", GLFW.GLFW_KEY_LEFT_SHIFT + GLFW.GLFW_KEY_2, Ref.MOD_NAME), 1);
        put(new KeyBinding("Spell Hotbar key 3", GLFW.GLFW_KEY_LEFT_SHIFT + GLFW.GLFW_KEY_3, Ref.MOD_NAME), 2);
        put(new KeyBinding("Spell Hotbar key 4", GLFW.GLFW_KEY_LEFT_SHIFT + GLFW.GLFW_KEY_4, Ref.MOD_NAME), 3);
        put(new KeyBinding("Spell Hotbar key 5", GLFW.GLFW_KEY_LEFT_SHIFT + GLFW.GLFW_KEY_5, Ref.MOD_NAME), 4);

    }};

    public static void register() {
        ClientRegistry.registerKeyBinding(Player_Stats);
        ClientRegistry.registerKeyBinding(Player_Stat_Points);
        ClientRegistry.registerKeyBinding(disableNeatOverlay);
        ClientRegistry.registerKeyBinding(Talent_Tree);
        ClientRegistry.registerKeyBinding(mapInfo);
        ClientRegistry.registerKeyBinding(hubScreen);

        for (KeyBinding entry : HOTBAR.keySet()) {
            ClientRegistry.registerKeyBinding(entry);
        }

    }

}
