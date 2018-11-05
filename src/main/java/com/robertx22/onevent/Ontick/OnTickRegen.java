package com.robertx22.onevent.ontick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.mmorpg.Main;
import com.robertx22.network.PlayerPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class OnTickRegen {

	static int regenTicks = 0;
	static int playerSyncTick = 0;
	static int mobsListSyncTick = 0;
	static int mobsSyncTick = 0;

	static final int TicksToUpdatePlayer = 20;
	static final int TicksToUpdateMobList = 120;
	static final int TicksToUpdateAllMobs = 200;
	static final int TicksToRegen = 100;

	static int radius = 50;

	static HashMap<UUID, EntityUpdate> Map = new HashMap<UUID, EntityUpdate>();

	private static void UpdateEntityList(EntityPlayerMP player) {

		List<EntityLivingBase> entities = new ArrayList<EntityLivingBase>();

		for (Entity en : player.world.getEntitiesWithinAABBExcludingEntity(player,
				new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius,
						player.posX + radius, player.posY + radius, player.posZ + radius))) {
			if (en instanceof EntityLivingBase) {
				entities.add((EntityLivingBase) en);
			}
		}

		Map.put(player.getUniqueID(), new EntityUpdate(player, entities));

	}

	private static void UpdateMobs(EntityPlayerMP player) {

		EntityUpdate data = Map.get(player.getUniqueID());

		if (data == null) {
			UpdateEntityList(player);
			data = Map.get(player.getUniqueID());
		}

		if (data != null && !data.isFinished()) {
			float count = (float) data.current / (float) data.entities.size();
			float ticks = (float) mobsSyncTick / (float) TicksToUpdateAllMobs;

			if (ticks > count || data.current == 0) {
				data.update();
			}
		} else {
			mobsSyncTick = 0;
		}

	}

	@SubscribeEvent
	public static void onTickRegen(TickEvent.PlayerTickEvent event) {

		if (event.phase == Phase.END && event.side.equals(Side.SERVER)) {

			EntityPlayerMP player = (EntityPlayerMP) event.player;

			regenTicks++;
			mobsSyncTick++;
			playerSyncTick++;
			mobsListSyncTick++;

			UpdateMobs(player);

			if (mobsListSyncTick > TicksToUpdateMobList) {
				mobsListSyncTick = 0;
				try {
					UpdateEntityList(player);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (regenTicks > TicksToRegen) {

				if (player.isEntityAlive()) {
					Unit unit = UnitSaving.Load(player);
					unit.RecalculateStats(player);

					int manarestored = (int) unit.Stats.get(new ManaRegen().Name()).Value;
					unit.RestoreMana(manarestored);

					int energyrestored = (int) unit.Stats.get(new EnergyRegen().Name()).Value;
					unit.RestoreEnergy(energyrestored);

					int healthrestored = (int) unit.Stats.get(new HealthRegen().Name()).Value;
					unit.Heal(player, healthrestored);

					UnitSaving.Save(player, unit);

					regenTicks = 0;
				}
			}

			if (playerSyncTick > TicksToUpdatePlayer) {
				playerSyncTick = 0;

				String json = player.getCapability(EntityData.Data, null).getNBT().getString(UnitSaving.DataLocation);

				if (json != null && !json.isEmpty()) {
					PlayerPackage playerpacket = new PlayerPackage(json);
					Main.Network.sendTo(playerpacket, (EntityPlayerMP) player);

				}

			}

		}

	}

}
