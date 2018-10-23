package com.robertx22.onevent.ontick;

import com.robertx22.database.stats.types.EnergyRegen;
import com.robertx22.database.stats.types.ManaRegen;
import com.robertx22.network.Network;
import com.robertx22.network.StringPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Saving;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber
public class OnTickRegen {

	static int tick = 0;

	static int syncTick = 0;

	static int time = 80;

	@SubscribeEvent
	public static void onTickRegen(TickEvent.PlayerTickEvent event) {

		if (event.phase == Phase.END && event.side.isServer()) {

			tick++;

			syncTick++;

			if (syncTick > 10) {
				Unit unit = UnitSaving.Load(event.player);
				StringPackage packet = new StringPackage(Saving.ToString(unit));
				Network.INSTANCE.sendTo(packet, (EntityPlayerMP) event.player);

				syncTick = 0;
			}

			if (tick > time) {

				Unit unit = UnitSaving.Load(event.player);
				unit.RecalculateStats(event.player);

				int manarestored = (int) unit.Stats.get(new ManaRegen().Name()).Value;
				unit.RestoreMana(manarestored);

				int energyrestored = (int) unit.Stats.get(new EnergyRegen().Name()).Value;
				unit.RestoreEnergy(energyrestored);

				UnitSaving.Save(event.player, unit);

				tick = 0;

			}

		}

	}

}
