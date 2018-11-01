package com.robertx22.onevent.ontick;

import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.network.EntityPackage;
import com.robertx22.network.Network;
import com.robertx22.network.PlayerPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;
import com.robertx22.uncommon.datasaving.bases.Saving;
import com.robertx22.uncommon.gui.BarsGUI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber
public class OnTickRegen {

	static int tick = 0;

	static int playerSyncTick = 0;
	static int mobsSyncTick = 0;

	static int time = 100;

	@SubscribeEvent
	public static void onTickRegen(TickEvent.PlayerTickEvent event) {

		if (event.phase == Phase.END && event.side.isServer()) {

			tick++;

			playerSyncTick++;
			mobsSyncTick++;

			if (mobsSyncTick > 100) {
				try {
					for (Entity en : event.player.world.loadedEntityList) {
						if (en instanceof EntityLivingBase) {

							String json = en.getCapability(EntityData.Data, null).getNBT()
									.getString(UnitSaving.DataLocation);

							if (json != null && !json.isEmpty()) {
								EntityPackage mobpacket = new EntityPackage(json);
								Network.INSTANCE.sendTo(mobpacket, (EntityPlayerMP) event.player);
							}

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (playerSyncTick > 20) {
				Unit unit = UnitSaving.Load(event.player);
				PlayerPackage packet = new PlayerPackage(Saving.ToString(unit));
				Network.INSTANCE.sendTo(packet, (EntityPlayerMP) event.player);

				BarsGUI.Updated = true;
				playerSyncTick = 0;
			}

			if (tick > time) {

				if (event.player.isEntityAlive()) {
					Unit unit = UnitSaving.Load(event.player);
					unit.RecalculateStats(event.player);

					int manarestored = (int) unit.Stats.get(new ManaRegen().Name()).Value;
					unit.RestoreMana(manarestored);

					int energyrestored = (int) unit.Stats.get(new EnergyRegen().Name()).Value;
					unit.RestoreEnergy(energyrestored);

					int healthrestored = (int) unit.Stats.get(new HealthRegen().Name()).Value;
					unit.Heal(event.player, healthrestored);
					UnitSaving.Save(event.player, unit);

					tick = 0;
				}
			}

		}

	}

}
