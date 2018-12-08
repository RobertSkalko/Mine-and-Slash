package com.robertx22.onevent.ontick;

import java.util.HashMap;
import java.util.UUID;

import com.robertx22.customitems.misc.ItemMapBackPortal;
import com.robertx22.database.stat_types.resources.EnergyRegen;
import com.robertx22.database.stat_types.resources.HealthRegen;
import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.mmorpg.Main;
import com.robertx22.network.WorldPackage;
import com.robertx22.saveclasses.Unit;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

@Mod.EventBusSubscriber
public class OnTickLogic {

    static final int TicksToUpdatePlayer = 18;
    static final int TicksToRegen = 100;
    static final int TicksToGiveMapPortal = 400;
    static final int TicksToUpdateWorld = 120;

    public static HashMap<UUID, PlayerTickData> PlayerTickDatas = new HashMap<UUID, PlayerTickData>();

    @SubscribeEvent
    public static void onTickLogic(TickEvent.ServerTickEvent event) {

	if (event.phase == Phase.END && event.side.isServer()) {

	    try {
		for (EntityPlayerMP player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
			.getPlayers()) {

		    PlayerTickData data = null;

		    if (PlayerTickDatas.containsKey(player.getUniqueID())) {
			data = PlayerTickDatas.get(player.getUniqueID());
		    } else {
			data = new PlayerTickData();
		    }

		    data.increment();

		    if (data.regenTicks > TicksToRegen) {
			data.regenTicks = 0;
			if (player.isEntityAlive()) {

			    IWorldData mapdata = Load.World(player.world);
			    UnitData unit_capa = player.getCapability(EntityData.Data, null);
			    unit_capa.recalculateStats(player, mapdata);
			    Unit unit = unit_capa.getUnit();

			    int manarestored = (int) unit.MyStats.get(new ManaRegen().Guid()).Value;
			    unit_capa.restoreMana(manarestored);

			    int energyrestored = (int) unit.MyStats.get(new EnergyRegen().Guid()).Value;
			    unit_capa.restoreEnergy(energyrestored);

			    int healthrestored = (int) unit.MyStats.get(new HealthRegen().Guid()).Value;
			    unit_capa.heal(player, healthrestored);

			    unit_capa.setUnit(unit, player);

			}
		    }

		    if (data.worldUpdateTicks > TicksToUpdateWorld) {
			data.worldUpdateTicks = 0;
			IWorldData mapdata = Load.World(player.world);
			if (mapdata.isMapWorld()) {
			    Main.Network.sendTo(new WorldPackage(mapdata), player);
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
			UnitData unit_capa = player.getCapability(EntityData.Data, null);
			unit_capa.syncToClient(player);

		    }
		    if (data != null) {
			PlayerTickDatas.put(player.getUniqueID(), data);
		    }
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}

    }

    static class PlayerTickData {
	public int regenTicks = 0;
	public int playerSyncTick = 0;
	public int mapPortalTicks = 0;
	public int worldUpdateTicks = 0;

	public void increment() {
	    regenTicks++;
	    playerSyncTick++;
	    mapPortalTicks++;
	    worldUpdateTicks++;
	}

    }

}
