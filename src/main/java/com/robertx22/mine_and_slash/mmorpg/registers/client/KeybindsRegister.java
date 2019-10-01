package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeybindsRegister {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stat Overview", GLFW.GLFW_KEY_P, Ref.MOD_NAME);
    public static KeyBinding Player_Stat_Points = new KeyBinding("Player Stat Points", GLFW.GLFW_KEY_O, Ref.MOD_NAME);
    public static KeyBinding Talent_Tree = new KeyBinding("Talent Tree", GLFW.GLFW_KEY_J, Ref.MOD_NAME);
    public static KeyBinding disableNeatOverlay = new KeyBinding("Disable Neat Overlay", 0, Ref.MOD_NAME);

    public static void register() {
        ClientRegistry.registerKeyBinding(Player_Stats);
        ClientRegistry.registerKeyBinding(Player_Stat_Points);
        ClientRegistry.registerKeyBinding(disableNeatOverlay);

        if (MMORPG.RUN_DEV_TOOLS) {
            ClientRegistry.registerKeyBinding(Talent_Tree);
        }
    }

}
