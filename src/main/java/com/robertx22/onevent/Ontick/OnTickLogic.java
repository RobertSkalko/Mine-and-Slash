package com.robertx22.onevent.ontick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.robertx22.customitems.misc.ItemMapBackPortal;
import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.mmorpg.Main;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber
public class OnTickLogic {

	static final int TicksToUpdatePlayer = 15;
	static final int TicksToUpdateMobList = 120;
	static final int TicksToUpdateAllMobs = 200;
	static final int TicksToRegen = 100;
	static final int TicksToGiveMapPortal = 200;

	static int radius = 50;

	static HashMap<UUID, EntityUpdate> Map = new HashMap<UUID, EntityUpdate>();
	public static HashMap<UUID, PlayerTickData> PlayerTickDatas = new HashMap();

	@SubscribeEvent
	public static void onTickLogic(TickEvent.ServerTickEvent event) {

		if (event.phase == Phase.END && event.side.equals(Side.SERVER)) {

			for (EntityPlayerMP player : Main.server.getPlayerList().getPlayers()) {

				UnitData unit_capa = player.getCapability(EntityData.Data, null);

				PlayerTickData data = null;

				if (PlayerTickDatas.containsKey(player.getUniqueID())) {
					data = PlayerTickDatas.get(player.getUniqueID());
				} else {
					data = new PlayerTickData();
				}

				data.increment();

				UpdateMobs(player, data);

				if (data.mobsListSyncTick > TicksToUpdateMobList) {
					data.mobsListSyncTick = 0;
					try {
						UpdateEntityList(player);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (data.regenTicks > TicksToRegen) {

					if (player.isEntityAlive()) {
						Unit unit = UnitSaving.Load(player);
						unit.RecalculateStats(player, unit_capa.getLevel());

						int manarestored = (int) unit.MyStats.get(new ManaRegen().Name()).Value;
						unit.RestoreMana(manarestored);

						int energyrestored = (int) unit.MyStats.get(new EnergyRegen().Name()).Value;
						unit.RestoreEnergy(energyrestored);

						int healthrestored = (int) unit.MyStats.get(new HealthRegen().Name()).Value;
						unit.Heal(player, healthrestored);

						unit_capa.setUnit(unit, player);
						data.regenTicks = 0;
					}
				}

				if (data.mapPortalTicks > TicksToGiveMapPortal) {
					data.mapPortalTicks = 0;

					IWorldData mapdata = Load.World(player.world);

					if (mapdata.isMapWorld()) {
						ItemStack portalitem = new ItemStack(ItemMapBackPortal.ITEM);
						if (!player.inventory.hasItemStack(portalitem)) {
							player.inventory.addItemStackToInventory(portalitem);

						}
					}
				}

				if (data.playerSyncTick > TicksToUpdatePlayer) {
					data.playerSyncTick = 0;

					unit_capa.syncToClient(player);

				}
				if (data != null) {
					PlayerTickDatas.put(player.getUniqueID(), data);
				}
			}

		}

	}

	private static void UpdateEntityList(EntityPlayerMP player) {

		List<EntityLivingBase> entities = new ArrayList<EntityLivingBase>();

		for (Entity en : player.world.getEntitiesWithinAABBExcludingEntity(player,
				new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius,
						player.posX + radius, player.posY + radius, player.posZ + radius))) {
			if (en.hasCapability(EntityData.Data, null)) {
				entities.add((EntityLivingBase) en);
			}
		}

		Map.put(player.getUniqueID(), new EntityUpdate(player, entities));

	}

	private static void UpdateMobs(EntityPlayerMP player, PlayerTickData syncData) {

		EntityUpdate data = Map.get(player.getUniqueID());

		if (data == null) {
			UpdateEntityList(player);
			data = Map.get(player.getUniqueID());
		}

		if (data != null && !data.isFinished()) {
			float count = (float) data.current / (float) data.entities.size();
			float ticks = (float) syncData.mobsSyncTick / (float) TicksToUpdateAllMobs;

			if (ticks > count || data.current == 0) {
				data.update();
			}
		} else {
			syncData.mobsSyncTick = 0;
		}

	}

	static class PlayerTickData {
		public int regenTicks = 0;
		public int playerSyncTick = 0;
		public int mobsListSyncTick = 0;
		public int mobsSyncTick = 0;
		public int mapPortalTicks = 0;

		public void increment() {
			regenTicks++;
			mobsSyncTick++;
			playerSyncTick++;
			mobsListSyncTick++;
			mapPortalTicks++;
		}

	}

}
