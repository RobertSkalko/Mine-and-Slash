package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.a_libraries.neat_mob_overlay.NeatConfig;
import com.robertx22.mine_and_slash.gui.main_hub.MainHubScreen;
import com.robertx22.mine_and_slash.gui.map_info_gui.MapInfoScreen;
import com.robertx22.mine_and_slash.gui.stat_allocation_screen.StatAllocationScreen;
import com.robertx22.mine_and_slash.gui.stats_overview.StatOverviewScreen;
import com.robertx22.mine_and_slash.gui.talent_tree_gui.TalentPerkTreeScreen;
import com.robertx22.mine_and_slash.mmorpg.registers.client.KeybindsRegister;
import net.minecraft.client.Minecraft;
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

                if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new StatOverviewScreen());
                } else if (key == KeybindsRegister.Player_Stat_Points.getKey()
                        .getKeyCode()) {
                    mc.displayGuiScreen(new StatAllocationScreen());
                } else if (key == KeybindsRegister.Talent_Tree.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new TalentPerkTreeScreen());
                } else if (key == KeybindsRegister.mapInfo.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new MapInfoScreen());
                } else if (key == KeybindsRegister.hubScreen.getKey().getKeyCode()) {
                    mc.displayGuiScreen(new MainHubScreen());
                }

                boolean wasDown = down;
                down = KeybindsRegister.disableNeatOverlay.isKeyDown();
                if (mc.isGameFocused() && down && !wasDown) {
                    NeatConfig.draw = !NeatConfig.draw;
                }
            } else {

                if (key == KeybindsRegister.Player_Stats.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof StatOverviewScreen) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.Player_Stat_Points.getKey()
                        .getKeyCode()) {
                    if (mc.currentScreen instanceof StatAllocationScreen) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.Talent_Tree.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof TalentPerkTreeScreen) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.mapInfo.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof MapInfoScreen) {
                        mc.displayGuiScreen(null);
                    }
                } else if (key == KeybindsRegister.hubScreen.getKey().getKeyCode()) {
                    if (mc.currentScreen instanceof MainHubScreen) {
                        mc.displayGuiScreen(null);
                    }
                }

            }
        }

    }
}
