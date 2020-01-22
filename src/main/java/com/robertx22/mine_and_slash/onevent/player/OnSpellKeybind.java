package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.gui.spell_hotbar.SpellHotbarOverlay;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mine_and_slash.packets.spells.CastSpellPacket;
import com.robertx22.mine_and_slash.saveclasses.spells.PlayerSpellsData;
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

            if (key == KeybindsRegister.swapHotbar.getKey().getKeyCode()) {
                SpellHotbarOverlay.CURRENT_HOTBAR =
                        SpellHotbarOverlay.CURRENT_HOTBAR == PlayerSpellsData.Hotbar.FIRST ?
                                PlayerSpellsData.Hotbar.SECOND : PlayerSpellsData.Hotbar.FIRST;
            }

            for (KeyBinding entry : KeybindsRegister.HOTBAR.keySet()) {
                if (key == entry.getKey().getKeyCode()) {
                    MMORPG.sendToServer(
                            new CastSpellPacket(KeybindsRegister.HOTBAR.get(entry), SpellHotbarOverlay.CURRENT_HOTBAR));
                }
            }

        }
    }
}
