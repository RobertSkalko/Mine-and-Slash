package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mine_and_slash.network.CastSpellPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnSpellKeybind {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        Minecraft mc = Minecraft.getInstance();
        int key = event.getKey();

        if (mc.currentScreen == null) {

            for (KeyBinding entry : KeybindsRegister.HOTBAR.keySet()) {
                if (key == entry.getKey().getKeyCode()) {
                    MMORPG.sendToServer(new CastSpellPacket(KeybindsRegister.HOTBAR.get(entry)));

                }

            }
        }
    }
}
