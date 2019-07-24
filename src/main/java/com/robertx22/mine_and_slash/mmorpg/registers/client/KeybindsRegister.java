package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeybindsRegister {

    public static KeyBinding Player_Stats = new KeyBinding("Player Stats", GLFW.GLFW_KEY_P, Ref.MOD_NAME);

    public static KeyBinding Map_Stats = new KeyBinding("Current Map Stats", GLFW.GLFW_KEY_F7, Ref.MOD_NAME);

    public static KeyBinding Map_Drops = new KeyBinding("What Unique Items Current Map can drop", GLFW.GLFW_KEY_F6, Ref.MOD_NAME);

    public static void register() {
        ClientRegistry.registerKeyBinding(Player_Stats);
        //ClientRegistry.registerKeyBinding(Map_Stats);
        //ClientRegistry.registerKeyBinding(Map_Drops);

    }

}

//EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
// IInteractionObject interact = (IInteractionObject) tile;
//  NetworkHooks.openGui(entityPlayerMP, interact, pos);