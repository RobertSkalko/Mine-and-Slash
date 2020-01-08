package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeybindsRegister {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stat Overview", GLFW.GLFW_KEY_P, Ref.MOD_NAME);
    public static KeyBinding Player_Stat_Points = new KeyBinding("Player Stat Points", GLFW.GLFW_KEY_O, Ref.MOD_NAME);
    public static KeyBinding Talent_Tree = new KeyBinding("Talent Tree", GLFW.GLFW_KEY_J, Ref.MOD_NAME);
    public static KeyBinding disableNeatOverlay = new KeyBinding("Disable Neat Overlay", GLFW.GLFW_KEY_MINUS, Ref.MOD_NAME);
    public static KeyBinding mapInfo = new KeyBinding("Adventure Map Info", GLFW.GLFW_KEY_M, Ref.MOD_NAME);
    public static KeyBinding hubScreen = new KeyBinding("Hub Screen", GLFW.GLFW_KEY_H, Ref.MOD_NAME);

    public static void register() {
        ClientRegistry.registerKeyBinding(Player_Stats);
        ClientRegistry.registerKeyBinding(Player_Stat_Points);
        ClientRegistry.registerKeyBinding(disableNeatOverlay);
        ClientRegistry.registerKeyBinding(Talent_Tree);
        ClientRegistry.registerKeyBinding(mapInfo);
        ClientRegistry.registerKeyBinding(hubScreen);

    }

}
