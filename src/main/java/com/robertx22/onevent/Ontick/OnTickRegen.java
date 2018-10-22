package com.robertx22.onevent.ontick;

import com.robertx22.database.stats.types.ManaRegen;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber
public class OnTickRegen {

	static int tick = 0;

	static int time = 150;

	@SubscribeEvent
	public static void onTickRegen(TickEvent.PlayerTickEvent event) {

		if (event.phase == Phase.END && event.side.isServer()) {

			tick++;

			if (tick > time) {

				Unit unit = UnitSaving.Load(event.player);
				unit.RecalculateStats(event.player);

				int manarestored = (int) unit.Stats.get(new ManaRegen().Name()).Value;
				unit.RestoreMana((int) unit.Stats.get(new ManaRegen().Name()).Value);

				event.player.sendMessage(new TextComponentString("Regenerating " + manarestored + " Mana"));

				tick = 0;

				UnitSaving.Save(event.player, unit);

			}

		}

	}

}
