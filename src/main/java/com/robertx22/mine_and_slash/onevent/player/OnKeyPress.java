package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.uncommon.gui.StatGUI;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnKeyPress {

    // they said i should use clienttickevent but idk how
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        int key = event.getKey();

        if (Minecraft.getInstance().currentScreen == null) { // public net.minecraft.client.gui.screen.Screen field_71462_r

            if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {

                Minecraft.getInstance().displayGuiScreen(new StatGUI());
            }
        }

    }
}
