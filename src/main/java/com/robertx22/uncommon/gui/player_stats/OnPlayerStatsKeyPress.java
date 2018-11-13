package com.robertx22.uncommon.gui.player_stats;

import com.rabbit.gui.RabbitGui;
import com.robertx22.mmorpg.Keybinds;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public class OnPlayerStatsKeyPress {

	public static StatsGui player_stats_gui = new StatsGui();

	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event) {

		if (Keybinds.Player_Stats.isPressed()) {

			RabbitGui.proxy.display(new StatsGui());

		}
	}
}
