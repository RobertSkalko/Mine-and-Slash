package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.database.EntityConfig;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.world.AntiMobFarmCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnMobDeathDrops {

    @SubscribeEvent
    public static void mobOnDeathDrop(LivingDeathEvent event) {

        try {

            LivingEntity mobKilled = event.getEntityLiving();

            if (mobKilled.world.isRemote) {
                return;
            }

            if (!(mobKilled instanceof PlayerEntity)) {
                if (Load.hasUnit(mobKilled)) {

                    UnitData mobKilledData = Load.Unit(mobKilled);

                    Entity killerEntity = mobKilledData.getHighestDamageEntity(mobKilled);

                    if (killerEntity instanceof ServerPlayerEntity) {

                        ServerPlayerEntity player = (ServerPlayerEntity) killerEntity;
                        UnitData playerData = Load.Unit(player);

                        EntityConfig config = SlashRegistry.getEntityConfig(mobKilled, mobKilledData);

                        float loot_multi = (float) config.loot_multi;

                        if (loot_multi > 0) {
                            player.world.getCapability(AntiMobFarmCap.Data)
                                .ifPresent(x -> x.onValidMobDeathByPlayer(mobKilled));
                        }

                        if (loot_multi > 0) {
                            MasterLootGen.genAndDrop(mobKilledData, playerData, mobKilled, player);
                        }

                    }
                }

            }

        } catch (

            Exception e) {
            e.printStackTrace();
        }

    }

}
