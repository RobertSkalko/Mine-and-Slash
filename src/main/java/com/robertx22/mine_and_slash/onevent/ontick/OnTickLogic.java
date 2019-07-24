package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.database.stats.stat_types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.ManaRegen;
import com.robertx22.mine_and_slash.items.misc.ItemMapBackPortal;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

import java.util.HashMap;
import java.util.UUID;

public class OnTickLogic {

    static final int TicksToUpdatePlayer = 18;
    static final int TicksToRegen = 100;
    static final int TicksToGiveMapPortal = 400;
    static final int TicksToPassMinute = 1200;

    public static HashMap<UUID, PlayerTickData> PlayerTickDatas = new HashMap<UUID, PlayerTickData>();

    @SubscribeEvent
    public static void onTickLogicVoid(TickEvent.PlayerTickEvent event) {

        if (event.side.equals(LogicalSide.SERVER) && event.phase == Phase.END) {

            try {

                ServerPlayerEntity player = (ServerPlayerEntity) event.player;

                PlayerTickData data = PlayerTickDatas.get(player.getUniqueID());

                if (data == null) {
                    data = new PlayerTickData();
                }

                data.increment();

                if (data.regenTicks > TicksToRegen) {
                    data.regenTicks = 0;
                    if (player.isAlive()) {

                        UnitData unit_capa = Load.Unit(player);
                        unit_capa.forceRecalculateStats(player); // has to do this cus curios doesnt call equipsChanged event
                        Unit unit = unit_capa.getUnit();

                        int manarestored = (int) unit.getStat(new ManaRegen()).Value;
                        unit_capa.restoreMana(manarestored);

                        int energyrestored = (int) unit.getStat(new EnergyRegen()).Value;
                        unit_capa.restoreEnergy(energyrestored);

                        int healthrestored = (int) unit.getStat(new HealthRegen()).Value;
                        unit_capa.heal(new HealData(player, unit_capa, healthrestored));

                    }
                }

                if (data.mapPortalTicks > TicksToGiveMapPortal) {
                    data.mapPortalTicks = 0;

                    if (WorldUtils.isMapWorld(player.world)) {
                        ItemStack portalitem = new ItemStack(ItemMapBackPortal.ITEM);
                        if (!player.inventory.hasItemStack(portalitem)) {
                            player.inventory.addItemStackToInventory(portalitem);

                        }
                    }

                }

                if (data.playerSyncTick > TicksToUpdatePlayer) {
                    data.playerSyncTick = 0;
                    Load.Unit(player).syncToClient(player);
                }
                if (data.ticksToPassMinute > TicksToPassMinute) {
                    data.ticksToPassMinute = 0;
                    if (WorldUtils.isMapWorld(player.world)) {
                        Load.playerMapData(player).onMinute(player);
                    }
                }

                PlayerTickDatas.put(player.getUniqueID(), data);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    static class PlayerTickData {
        public int regenTicks = 0;
        public int playerSyncTick = 0;
        public int mapPortalTicks = 0;
        public int ticksToPassMinute = 0;

        public void increment() {
            regenTicks++;
            playerSyncTick++;
            mapPortalTicks++;
            ticksToPassMinute++;
        }

    }

}
