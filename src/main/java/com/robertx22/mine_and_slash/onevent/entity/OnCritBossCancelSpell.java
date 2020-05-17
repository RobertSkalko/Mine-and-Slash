package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.entities.IBossMob;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnCritBossCancelSpell {

    @SubscribeEvent
    public static void event(CriticalHitEvent event) {

        if (event.getTarget() instanceof IBossMob) {
            IBossMob boss = (IBossMob) event.getTarget();
            boss.onCrit((LivingEntity) event.getTarget());
        }

    }

}
