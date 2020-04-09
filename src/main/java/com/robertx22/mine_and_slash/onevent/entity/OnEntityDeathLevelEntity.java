package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnEntityDeathLevelEntity {

    @SubscribeEvent
    public static void mobOnDeathDrop(LivingDeathEvent event) {

        try {

            LivingEntity mobKilled = event.getEntityLiving();

            if (mobKilled.world.isRemote) {
                return;
            }

            if (event.getSource() != null && event.getSource()
                .getTrueSource() instanceof LivingEntity) {

                LivingEntity killer = (LivingEntity) event.getSource()
                    .getTrueSource();

                if (killer instanceof PlayerEntity == false) {

                    EntityCap.UnitData unit = Load.Unit(killer);

                    if (RandomUtils.roll(3)) {
                        unit.setLevel(unit.getLevel() + 1, killer);
                    }
                    if (RandomUtils.roll(0.5F)) {
                        unit.setRarity(unit.getRarity() + 1);
                    }

                }

            }

        } catch (

            Exception e) {
            e.printStackTrace();

        }
    }
}