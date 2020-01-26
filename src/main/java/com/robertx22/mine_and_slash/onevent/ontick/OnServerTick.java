package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShieldRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.items.misc.ItemMapBackPortal;
import com.robertx22.mine_and_slash.professions.blocks.bases.ProfessionContainer;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.capability.bases.CapSyncUtil;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.HashMap;
import java.util.UUID;

public class OnServerTick {

    static final int TicksToUpdatePlayer = 18;
    static final int TicksToRegen = 100;
    static final int TicksToGiveMapPortal = 400;
    static final int TicksToPassMinute = 1200;
    static final int TicksToSpellCooldowns = 1;

    public static HashMap<UUID, PlayerTickData> PlayerTickDatas = new HashMap<UUID, PlayerTickData>();

    @SubscribeEvent
    public static void onTickLogicVoid(TickEvent.PlayerTickEvent event) {

        if (event.side.equals(LogicalSide.SERVER) && event.phase == TickEvent.Phase.END) {

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
                        unit_capa.forceRecalculateStats(player); // has to do
                        // this cus curios doesnt call
                        // equipsChanged event - actually
                        // there's one, but i fear  bugs
                        Unit unit = unit_capa.getUnit();

                        int manarestored = (int) unit.getStat(ManaRegen.GUID).val;
                        ResourcesData.Context mana = new ResourcesData.Context(unit_capa, player,
                                                                               ResourcesData.Type.MANA, manarestored,
                                                                               ResourcesData.Use.RESTORE
                        );
                        unit_capa.getResources().modify(mana);

                        int energyrestored = (int) unit.getStat(EnergyRegen.GUID).val;
                        ResourcesData.Context ene = new ResourcesData.Context(unit_capa, player,
                                                                              ResourcesData.Type.ENERGY, energyrestored,
                                                                              ResourcesData.Use.RESTORE
                        );
                        unit_capa.getResources().modify(ene);

                        int healthrestored = (int) unit.getStat(HealthRegen.GUID).val;
                        ResourcesData.Context hp = new ResourcesData.Context(unit_capa, player,
                                                                             ResourcesData.Type.HEALTH, healthrestored,
                                                                             ResourcesData.Use.RESTORE
                        );
                        unit_capa.getResources().modify(hp);

                        int magicshieldrestored = (int) unit.getStat(MagicShieldRegen.GUID).val;
                        ResourcesData.Context ms = new ResourcesData.Context(unit_capa, player,
                                                                             ResourcesData.Type.MAGIC_SHIELD,
                                                                             magicshieldrestored,
                                                                             ResourcesData.Use.RESTORE
                        );
                        unit_capa.getResources().modify(ms);

                    }
                }

                if (data.mapPortalTicks > TicksToGiveMapPortal) {
                    data.mapPortalTicks = 0;

                    if (WorldUtils.isMapWorldClass(player.world)) {
                        ItemStack portalitem = new ItemStack(ItemMapBackPortal.ITEM);
                        if (!player.inventory.hasItemStack(portalitem)) {
                            player.inventory.addItemStackToInventory(portalitem);

                        }
                    }

                }
                if (data.ticksToPassMinute > TicksToPassMinute) {
                    data.ticksToPassMinute = 0;
                    if (WorldUtils.isMapWorldClass(player.world)) {
                        Load.playerMapData(player).onMinute(player);
                    }
                }

                if (data.playerSyncTick > TicksToUpdatePlayer) {
                    data.playerSyncTick = 0;

                    if (player.openContainer instanceof ProfessionContainer) {
                        ProfessionContainer prof = (ProfessionContainer) player.openContainer;
                        prof.tile.onOpenByPlayer(player);
                    }

                    CapSyncUtil.syncAll(player);

                }

                if (data.ticksToSpellCooldowns >= TicksToSpellCooldowns) {
                    data.ticksToSpellCooldowns = 0;
                    PlayerSpellCap.ISpellsCap spells = Load.spells(player);

                    spells.getSpellData().onTimePass(TicksToSpellCooldowns);
                    spells.getSpellData().tryCast(player, spells);

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
        public int ticksToSpellCooldowns = 0;

        public void increment() {
            regenTicks++;
            playerSyncTick++;
            mapPortalTicks++;
            ticksToPassMinute++;
            ticksToSpellCooldowns++;
        }

    }

}
