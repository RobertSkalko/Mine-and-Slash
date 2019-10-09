package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.NeatConfig;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.gui.PerkTreeScreen;
import com.robertx22.mine_and_slash.uncommon.gui.stat_point_screen.StatPointScreen;
import com.robertx22.mine_and_slash.uncommon.gui.stats_gui.StatOverviewGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnKeyPress {

    static boolean down;

    // they said i should use clienttickevent but idk how
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

        int key = event.getKey();

        if (Minecraft.getInstance().currentScreen == null) { // public net.minecraft.client.gui.screen.Screen field_71462_r

            if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {
                Minecraft.getInstance().displayGuiScreen(new StatOverviewGUI());
            } else if (key == KeybindsRegister.Player_Stat_Points.getKey().getKeyCode()) {
                Minecraft.getInstance().displayGuiScreen(new StatPointScreen());
            } else if (key == KeybindsRegister.Talent_Tree.getKey().getKeyCode()) {
                Minecraft.getInstance().displayGuiScreen(new PerkTreeScreen());
            }

            boolean wasDown = down;
            down = KeybindsRegister.disableNeatOverlay.isKeyDown();
            if (Minecraft.getInstance().isGameFocused() && down && !wasDown)
                NeatConfig.draw = !NeatConfig.draw;

        }

    }
}
