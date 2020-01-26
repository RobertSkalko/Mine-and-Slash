package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.gui.main_hub.MainHubScreen;
import com.robertx22.mine_and_slash.gui.spell_hotbar.SpellHotbarOverlay;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mine_and_slash.packets.spells.CastSpellPacket;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class OnKeyPress {

    static boolean down;

    // they said i should use clienttickevent but idk how
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        Minecraft mc = Minecraft.getInstance();

        int key = event.getKey();

        if (event.getAction() == GLFW.GLFW_RELEASE) {

            if (mc.currentScreen == null) {

                if (key == KeybindsRegister.hubScreen.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new MainHubScreen());

                } else if (key == KeybindsRegister.swapHotbar.getKey().getKeyCode()) {

                    SpellHotbarOverlay.CURRENT_HOTBAR =
                            SpellHotbarOverlay.CURRENT_HOTBAR == PlayerSpellsData.Hotbar.FIRST ?
                                    PlayerSpellsData.Hotbar.SECOND : PlayerSpellsData.Hotbar.FIRST;
                } else {

                    for (KeyBinding entry : KeybindsRegister.HOTBAR.keySet()) {
                        if (key == entry.getKey().getKeyCode()) {
                            MMORPG.sendToServer(new CastSpellPacket(KeybindsRegister.HOTBAR.get(entry),
                                                                    SpellHotbarOverlay.CURRENT_HOTBAR
                            ));
                        }
                    }
                }
            } else {

                if (key == KeybindsRegister.hubScreen.getKey().getKeyCode()) {

                    if (mc.currentScreen instanceof INamedScreen) {
                        mc.displayGuiScreen(null);
                    }

                }

            }
        }

    }
}
