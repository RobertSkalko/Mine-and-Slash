package com.robertx22.mine_and_slash.onevent.ontick;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShieldRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.new_content.ProcessChunkBlocks;
import com.robertx22.mine_and_slash.potion_effects.all.TeleportProtection;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.capability.bases.CapSyncUtil;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.HashMap;
import java.util.UUID;

public class OnServerTick {

    static final int TicksToUpdatePlayer = 18;
    static final int TicksToRegen = 100;
    static final int TicksToPassMinute = 1200;
    static final int TicksToSpellCooldowns = 1;
    static final int TicksToProcessChunks = 50;

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

                        player.getCapability(EntityCap.Data)
                            .ifPresent(x -> {
                                x.forceRecalculateStats(player);
                                // has to do
                                // this cus curios doesnt call
                                // equipsChanged event - actually
                                // there's one, but i fear  bugs

                                Unit unit = x.getUnit();

                                float manarestored = unit.peekAtStat(ManaRegen.GUID)
                                    .getAverageValue();
                                ResourcesData.Context mana = new ResourcesData.Context(x, player, ResourcesData.Type.MANA,
                                    manarestored,
                                    ResourcesData.Use.RESTORE
                                );
                                x.getResources()
                                    .modify(mana);

                                float energyrestored = unit.peekAtStat(EnergyRegen.GUID)
                                    .getAverageValue();
                                ResourcesData.Context ene = new ResourcesData.Context(x, player, ResourcesData.Type.ENERGY,
                                    energyrestored,
                                    ResourcesData.Use.RESTORE
                                );
                                x.getResources()
                                    .modify(ene);

                                boolean restored = false;

                                boolean canHeal = player.getFoodStats()
                                    .getFoodLevel() >= 10;

                                if (canHeal) {
                                    if (player.getHealth() < player.getMaxHealth()) {
                                        restored = true;
                                    }

                                    float missingHp = x.getUnit()
                                        .getMissingHealth(player);

                                    float healthrestored = unit.peekAtStat(HealthRegen.GUID)
                                        .getAverageValue();
                                    ResourcesData.Context hp = new ResourcesData.Context(x, player, ResourcesData.Type.HEALTH,
                                        healthrestored,
                                        ResourcesData.Use.RESTORE
                                    );

                                    x.getResources()
                                        .modify(hp);

                                    if (x.getResources()
                                        .getMagicShield() < x.getUnit()
                                        .magicShieldData()
                                        .getAverageValue()) {
                                        restored = true;
                                    }

                                    float missingMs = x.getUnit()
                                        .magicShieldData()
                                        .getAverageValue() - x.getResources()
                                        .getMagicShield();

                                    float magicshieldrestored = unit.peekAtStat(MagicShieldRegen.GUID)
                                        .getAverageValue();
                                    ResourcesData.Context ms = new ResourcesData.Context(x, player,
                                        ResourcesData.Type.MAGIC_SHIELD,
                                        magicshieldrestored,
                                        ResourcesData.Use.RESTORE
                                    );
                                    x.getResources()
                                        .modify(ms);

                                    if (restored) {

                                        float hpRegenEffectiveness = MathHelper.clamp(missingHp / healthrestored, 0, 1);
                                        float msRegenEffectiveness = MathHelper.clamp(missingMs / magicshieldrestored, 0, 1);

                                        float maxHealthedEffectiveness = Math.max(hpRegenEffectiveness, msRegenEffectiveness);

                                        player.getFoodStats()
                                            .addExhaustion(ModConfig.INSTANCE.Server.REGEN_HUNGER_COST.get()
                                                .floatValue() / maxHealthedEffectiveness);

                                    }
                                }

                            });
                    }
                }

                if (data.ticksToPassMinute > TicksToPassMinute) {
                    data.ticksToPassMinute = 0;

                    Load.playerMapData(player)
                        .onMinute(player);

                    if (player.getServer()
                        .isSinglePlayer()) {
                        SlashRegistry.restoreFromBackupifEmpty();
                    }
                }
                if (data.ticksToProcessChunks > TicksToProcessChunks) {
                    data.ticksToProcessChunks = 0;

                    if (player.getActivePotionEffect(TeleportProtection.INSTANCE) == null) {
                        player.setInvulnerable(false);
                    }

                    ProcessChunkBlocks.process(player);
                }

                if (data.ticksToSpellCooldowns >= TicksToSpellCooldowns) {
                    data.ticksToSpellCooldowns = 0;

                    player.getCapability(PlayerSpellCap.Data)
                        .ifPresent(x -> {
                            x.getCastingData()
                                .onTimePass(player, x, TicksToSpellCooldowns);
                            x.getCastingData()
                                .tryCast(player, x);
                        });
                }

                if (data.playerSyncTick > TicksToUpdatePlayer) {
                    data.playerSyncTick = 0;

                    CapSyncUtil.syncAll(player);

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
        public int ticksToPassMinute = 0;
        public int ticksToSpellCooldowns = 0;
        public int ticksToProcessChunks = 0;

        public void increment() {
            regenTicks++;
            playerSyncTick++;
            ticksToPassMinute++;
            ticksToProcessChunks++;
            ticksToSpellCooldowns++;
        }

    }

}
