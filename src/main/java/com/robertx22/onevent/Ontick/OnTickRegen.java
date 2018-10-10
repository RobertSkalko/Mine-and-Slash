package com.robertx22.onevent.Ontick;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import java.util.Hashtable;

import com.robertx22.player.PlayerData;

public class OnTickRegen {

	int tick = 0;

	int time = 150;

	@SubscribeEvent
	public void onTickRegen(TickEvent.PlayerTickEvent event) {

		if (event.phase == Phase.END && event.side.isServer()) {

			tick++;

			if (tick > time) {

				/*
				 * EntityPlayer player = event.player;
				 * 
				 * Hashtable<String, Integer> stats = PlayerData.getStats(player);
				 * 
				 * int hpGainPercent = stats.get(Stats.HEALTH_REGEN.name); int manaGainPercent =
				 * stats.get(Stats.MANA_REGEN.name);
				 * 
				 * float hp = player.getMaxHealth(); int mana = stats.get(Stats.MANA.name);
				 * 
				 * player.heal(hpGainPercent * hp / 100);
				 * 
				 * tick = 0;
				 * 
				 */
			}

		}

	}

}
